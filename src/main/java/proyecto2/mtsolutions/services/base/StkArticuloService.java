package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.StkArticuloDTO;
public interface StkArticuloService {
    ListasDTO listadoStkArticulo(String usuario, int cantidad, int origen) throws Exception;
    String crearStkArticulo(StkArticuloDTO dto, String usuario) throws Exception;
}