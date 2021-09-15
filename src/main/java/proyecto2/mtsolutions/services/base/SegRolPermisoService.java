package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.SegRolPermisoDTO;
public interface SegRolPermisoService {
    ListasDTO listadoSegRolPermiso(String usuario, int cantidad, int origen) throws Exception;
    String crearSegRolPermiso(SegRolPermisoDTO dto, String usuario) throws Exception;
}