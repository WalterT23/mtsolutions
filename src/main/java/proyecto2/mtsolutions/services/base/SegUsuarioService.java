package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.SegUsuarioDTO;
public interface SegUsuarioService {
    ListasDTO listadoSegUsuario(String usuario, int cantidad, int origen) throws Exception;
    String crearSegUsuario(SegUsuarioDTO dto, String usuario) throws Exception;
}