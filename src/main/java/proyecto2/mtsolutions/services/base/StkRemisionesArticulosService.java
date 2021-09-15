package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.StkRemisionesArticulosDTO;
public interface StkRemisionesArticulosService {
    ListasDTO listadoStkRemisionesArticulos(String usuario, int cantidad, int origen) throws Exception;
    String crearStkRemisionesArticulos(StkRemisionesArticulosDTO dto, String usuario) throws Exception;
}