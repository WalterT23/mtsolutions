package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.SegFuncionalidadPermisoDTO;
public interface SegFuncionalidadPermisoService {
    ListasDTO listadoSegFuncionalidadPermiso(String usuario, int cantidad, int origen) throws Exception;
    String crearSegFuncionalidadPermiso(SegFuncionalidadPermisoDTO dto, String usuario) throws Exception;
}