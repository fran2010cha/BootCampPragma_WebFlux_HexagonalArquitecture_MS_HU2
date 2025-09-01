package co.com.pragma.r2dbc;

import co.com.pragma.model.tipoprestamo.TipoPrestamo;
import co.com.pragma.model.tipoprestamo.gateways.TipoPrestamoRepository;
import co.com.pragma.r2dbc.entities.TipoPrestamoEntity;
import co.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class TipoPrestamoRepositoryAdapter extends ReactiveAdapterOperations<
        TipoPrestamo/* change for domain model */,
        TipoPrestamoEntity/* change for adapter model */,
        Long,
        TipoPrestamoReactiveRepository
>  implements TipoPrestamoRepository {

    public TipoPrestamoRepositoryAdapter(TipoPrestamoReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, TipoPrestamo.class/* change for domain model */));
    }
    @Override
    public Mono<TipoPrestamo> save(TipoPrestamo tipoPrestamo) {
        TipoPrestamoEntity entity = mapper.map(tipoPrestamo, TipoPrestamoEntity.class);
        return repository.save(entity)
                .map(saved -> mapper.map(saved, TipoPrestamo.class));
    }

    @Override
    public Mono<TipoPrestamo> findById(Long id) {
        return repository.findById(id)
                .map(entity -> mapper.map(entity, TipoPrestamo.class));
    }

    @Override
    public Mono<TipoPrestamo> findByName(String name) {
        return repository.findByNombre(name)
                .map(entity -> mapper.map(entity, TipoPrestamo.class))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Tipo Prestamo no encontrado: " + name)));
    }

    @Override
    public Flux<TipoPrestamo> findAll() {
        return repository.findAll()
                .map(entity -> mapper.map(entity, TipoPrestamo.class));
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

}
