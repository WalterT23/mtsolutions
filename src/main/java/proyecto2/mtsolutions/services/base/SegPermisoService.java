package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.SegPermisoDTO;
public interface SegPermisoService {
    ListasDTO listadoSegPermiso(String usuario, int cantidad, int origen) throws Exception;
    String crearSegPermiso(SegPermisoDTO dto, String usuario) throws Exception;
}