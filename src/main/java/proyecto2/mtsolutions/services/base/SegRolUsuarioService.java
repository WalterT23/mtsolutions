package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.SegRolUsuarioDTO;
public interface SegRolUsuarioService {
    ListasDTO listadoSegRolUsuario(String usuario, int cantidad, int origen) throws Exception;
    String crearSegRolUsuario(SegRolUsuarioDTO dto, String usuario) throws Exception;
}