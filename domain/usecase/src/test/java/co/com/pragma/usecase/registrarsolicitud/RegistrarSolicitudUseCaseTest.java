package co.com.pragma.usecase.registrarsolicitud;

import co.com.pragma.model.estados.Estados;
import co.com.pragma.model.estados.gateways.EstadosRepository;
import co.com.pragma.model.solicitud.Solicitud;
import co.com.pragma.model.solicitud.gateways.SolicitudRepository;
import co.com.pragma.model.tipoprestamo.TipoPrestamo;
import co.com.pragma.model.tipoprestamo.gateways.TipoPrestamoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class RegistrarSolicitudUseCaseTest {


    private TipoPrestamoRepository tipoPrestamoRepository;
    private SolicitudRepository solicitudRepository;
    private EstadosRepository estadosRepository;

    private RegistrarSolicitudUseCase useCase;

    @BeforeEach
    void setUp() {
        tipoPrestamoRepository = mock(TipoPrestamoRepository.class);
        solicitudRepository = mock(SolicitudRepository.class);
        estadosRepository = mock(EstadosRepository.class);

        useCase = new RegistrarSolicitudUseCase(
                tipoPrestamoRepository, solicitudRepository, estadosRepository
        );
    }

    @Test
    void registerLoanRequest_success() {
        // Arrange
        TipoPrestamo tipoPrestamo = new TipoPrestamo();
        tipoPrestamo.setNombre("Préstamo Normal");

        Estados estado = new Estados();
        estado.setIdEstado(1L);
        estado.setNombre("Pendiente");

        Solicitud solicitud = new Solicitud();
        solicitud.setTipoPrestamo(tipoPrestamo);
        solicitud.setEstado(estado);
        /*solicitud.setUsuario_id("12222555");
        solicitud.setMonto(30000000.0);
        solicitud.setPlazoMeses(5);
        solicitud.setEmail("pablo5@gamil.com");*/

        when(tipoPrestamoRepository.findByName("Préstamo Normal"))
                .thenReturn(Mono.just(tipoPrestamo));
        when(estadosRepository.getEstadoById(1L))
                .thenReturn(Mono.just(estado));
        when(solicitudRepository.saveSolicitud(any(Solicitud.class)))
                .thenReturn(Mono.just(solicitud));

        // Act + Assert
        StepVerifier.create(useCase.registerLoanRequest(solicitud))
                .expectNextMatches(saved ->
                        saved.getTipoPrestamo().getNombre().equals("Préstamo Normal") &&
                                saved.getEstado().getIdEstado() == 1L
                )
                .verifyComplete();

        // Verify mocks were called
        verify(tipoPrestamoRepository).findByName("Préstamo Normal");
        verify(solicitudRepository).saveSolicitud(solicitud);
    }
}
