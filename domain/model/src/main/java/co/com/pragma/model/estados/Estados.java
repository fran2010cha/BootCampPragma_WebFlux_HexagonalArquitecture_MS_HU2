package co.com.pragma.model.estados;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Estados {
    private Long idEstado;
    private String nombre;

    @Override
    public String toString() {
        return "Estados{" +
                "idEstado=" + idEstado +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
