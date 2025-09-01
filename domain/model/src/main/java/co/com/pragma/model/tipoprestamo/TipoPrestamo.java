package co.com.pragma.model.tipoprestamo;
import lombok.*;

import java.math.BigDecimal;
import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class TipoPrestamo {

    private Long idTipoPrestamo;
    private String nombre;
    private BigDecimal montoMinimo;
    private BigDecimal montoMaximo;
    private BigDecimal tasaInteres;
    private Boolean validacionAutomatica;

    @Override
    public String toString() {
        return "TipoPrestamo{" +
                "idTipoPrestamo=" + idTipoPrestamo +
                ", nombre='" + nombre + '\'' +
                ", montoMinimo=" + montoMinimo +
                ", montoMaximo=" + montoMaximo +
                ", tasaInteres=" + tasaInteres +
                ", validacionAutomatica=" + validacionAutomatica +
                '}';
    }
}
