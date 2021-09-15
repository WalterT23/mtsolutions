package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.StkRemisionesDTO;
public interface StkRemisionesService {
    ListasDTO listadoStkRemisiones(String usuario, int cantidad, int origen) throws Exception;
    String crearStkRemisiones(StkRemisionesDTO dto, String usuario) throws Exception;
}