package co.com.pragma.api.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SolicitudRequestDTO {
    private String usuario_id;
    private String tipoPrestamoId;
    private BigDecimal monto;
    private Integer plazoMeses;
    private String email;
}
