package co.com.pragma.usecase.registrarsolicitud;

import co.com.pragma.model.estados.gateways.EstadosRepository;
import co.com.pragma.model.solicitud.Solicitud;
import co.com.pragma.model.solicitud.gateways.SolicitudRepository;
import co.com.pragma.model.tipoprestamo.gateways.TipoPrestamoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RegistrarSolicitudUseCase implements IRegistrarSolicitud {

    private final TipoPrestamoRepository tipoPrestamoReactRepository;
    private final SolicitudRepository solicitudRepository;
    private final EstadosRepository estadosRepository;

    @Override
    public Mono<Solicitud> registerLoanRequest(Solicitud solicitud) {

        return tipoPrestamoReactRepository.findByName(solicitud.getTipoPrestamo().getNombre())
                .doOnNext(saved -> System.out.println("Registro tipo prestacion: " + saved))
                .flatMap(tipoPrestamo -> {
                    solicitud.setTipoPrestamo(tipoPrestamo);
                    return solicitudRepository.saveSolicitud(solicitud);
                }).doOnNext(saved -> System.out.println("Registrando solicitud de prestamo: " + saved));
    }
}
