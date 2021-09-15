package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.StkAjusteInventarioDTO;
public interface StkAjusteInventarioService {
    ListasDTO listadoStkAjusteInventario(String usuario, int cantidad, int origen) throws Exception;
    String crearStkAjusteInventario(StkAjusteInventarioDTO dto, String usuario) throws Exception;
}