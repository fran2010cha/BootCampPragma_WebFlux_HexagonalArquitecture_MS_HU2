package co.com.pragma.model.solicitud;


import co.com.pragma.model.estados.Estados;
import co.com.pragma.model.tipoprestamo.TipoPrestamo;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class SolicitudTest {

    @Test
    void shouldCreateSolicitudWithAllArgsConstructor() {
        TipoPrestamo tipo = new TipoPrestamo(1L, "Personal",
                BigDecimal.valueOf(1000), BigDecimal.valueOf(5000),
                BigDecimal.valueOf(0.05), true);
        Estados estado = new Estados(1L, "Pendiente");

        Solicitud solicitud = new Solicitud(1L, "12345", tipo, estado,
                3000.0, 12, "user@mail.com");

        assertThat(solicitud.getIdSolicitud()).isEqualTo(1L);
        assertThat(solicitud.getUsuario_id()).isEqualTo("12345");
        assertThat(solicitud.getMonto()).isEqualTo(3000.0);
        assertThat(solicitud.getPlazoMeses()).isEqualTo(12);
        assertThat(solicitud.getEmail()).isEqualTo("user@mail.com");
        assertThat(solicitud.getTipoPrestamo().getNombre()).isEqualTo("Personal");
        assertThat(solicitud.getEstado().getNombre()).isEqualTo("Pendiente");
    }

    @Test
    void shouldBuildSolicitudWithBuilder() {
        Solicitud solicitud = Solicitud.builder()
                .idSolicitud(2L)
                .usuario_id("99999")
                .monto(2000.0)
                .plazoMeses(6)
                .email("builder@mail.com")
                .build();

        assertThat(solicitud.getUsuario_id()).isEqualTo("99999");
        assertThat(solicitud.getPlazoMeses()).isEqualTo(6);
        assertThat(solicitud.getEmail()).isEqualTo("builder@mail.com");
    }

}
