package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.SegRolDTO;
public interface SegRolService {
    ListasDTO listadoSegRol(String usuario, int cantidad, int origen) throws Exception;
    String crearSegRol(SegRolDTO dto, String usuario) throws Exception;
}