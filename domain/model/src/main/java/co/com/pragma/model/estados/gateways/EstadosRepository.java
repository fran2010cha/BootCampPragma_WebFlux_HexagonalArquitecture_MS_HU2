package co.com.pragma.model.estados.gateways;

import co.com.pragma.model.estados.Estados;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EstadosRepository {
    Mono<Estados> saveEstado(Estados estado);
    Mono<Estados> getEstadoById(Long id);
    Flux<Estados> getAllEstados();
}
