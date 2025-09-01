package co.com.pragma.usecase.registrarsolicitud;

import co.com.pragma.model.solicitud.Solicitud;
import reactor.core.publisher.Mono;

public interface IRegistrarSolicitud {
    Mono<Solicitud> registerLoanRequest(Solicitud solicitud);
}
