package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.StkInventarioDTO;
public interface StkInventarioService {
    ListasDTO listadoStkInventario(String usuario, int cantidad, int origen) throws Exception;
    String crearStkInventario(StkInventarioDTO dto, String usuario) throws Exception;
}