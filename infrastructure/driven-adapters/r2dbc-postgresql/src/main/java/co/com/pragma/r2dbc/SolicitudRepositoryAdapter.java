package co.com.pragma.r2dbc;

import co.com.pragma.model.solicitud.Solicitud;
import co.com.pragma.model.solicitud.gateways.SolicitudRepository;
import co.com.pragma.r2dbc.entities.SolicitudEntity;
import co.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

@Repository
public class SolicitudRepositoryAdapter extends ReactiveAdapterOperations<
        Solicitud,
        SolicitudEntity,
        Long,
        SolicitudReactRepository
        > implements SolicitudRepository {

    private static final Logger log = LoggerFactory.getLogger(SolicitudRepositoryAdapter.class);


    //para manejo de las transacciones dentro del flujo reactivo
    private final TransactionalOperator transactionalOperator;

    public SolicitudRepositoryAdapter(SolicitudReactRepository repository, ObjectMapper mapper, TransactionalOperator transactionalOperator) {
        super(repository, mapper, d -> mapper.map(d, Solicitud.class));
        this.transactionalOperator =  transactionalOperator;
    }

    // Dentro de tu adapter de persistencia de Solicitud
    private SolicitudEntity toEntity(Solicitud s) {
        return SolicitudEntity.builder()
                .idSolicitud(s.getIdSolicitud())
                .monto(s.getMonto() != null ? java.math.BigDecimal.valueOf(s.getMonto()) : null)
                .plazo(s.getPlazoMeses())
                .email(s.getEmail())
                .estadoId(s.getEstado() != null ? s.getEstado().getIdEstado() : null)
                .tipoPrestamoId(s.getTipoPrestamo() != null ? s.getTipoPrestamo().getIdTipoPrestamo() : null)
                .build();
    }

    private Solicitud toDomain(SolicitudEntity e, Solicitud original) {
        // reusa los objetos ya resueltos en el use case (estado y tipoPrestamo), solo setea el id generado
        return original.toBuilder()
                .idSolicitud(e.getIdSolicitud())
                .build();
    }

    @Override
    public Mono<Solicitud> saveSolicitud(Solicitud solicitud) {
        return repository.save(toEntity(solicitud))
                .map(saved -> toDomain(saved, solicitud))
                .doOnNext(u -> log.info("ADAPTER: Solicitud guardado en la BD: {}", u))
                .as(transactionalOperator::transactional);

    }

}
