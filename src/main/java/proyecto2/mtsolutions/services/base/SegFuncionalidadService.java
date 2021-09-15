package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.SegFuncionalidadDTO;
public interface SegFuncionalidadService {
    ListasDTO listadoSegFuncionalidad(String usuario, int cantidad, int origen) throws Exception;
    String crearSegFuncionalidad(SegFuncionalidadDTO dto, String usuario) throws Exception;
}