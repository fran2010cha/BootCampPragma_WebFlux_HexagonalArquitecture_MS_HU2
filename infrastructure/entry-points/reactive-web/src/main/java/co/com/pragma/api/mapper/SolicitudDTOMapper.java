package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.SolicitudRequestDTO;
import co.com.pragma.api.dto.SolicitudResponseDTO;
import co.com.pragma.model.estados.Estados;
import co.com.pragma.model.solicitud.Solicitud;
import co.com.pragma.model.tipoprestamo.TipoPrestamo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SolicitudDTOMapper {


    @Mapping(target = "idSolicitud", ignore = true)
    @Mapping(target = "tipoPrestamo", expression = "java(toTipoPrestamo(dto.getTipoPrestamoId()))")
    @Mapping(target = "estado", expression = "java(toEstadoPendiente())")
    Solicitud toEntity(SolicitudRequestDTO dto);


    default TipoPrestamo toTipoPrestamo(String nombre) {
        TipoPrestamo tp = new TipoPrestamo();
        tp.setNombre(nombre);
        return tp;
    }

    default Estados toEstadoPendiente() {
        Estados e = new Estados();
        e.setIdEstado(1L);
        e.setNombre("Pendiente de revisión");
        return e;
    }

    @Mapping(target = "estado", source = "estado.nombre")
    @Mapping(target = "tipoPrestamoNombre", source = "tipoPrestamo.nombre")
    SolicitudResponseDTO toResponseDTO(Solicitud entity);

}
