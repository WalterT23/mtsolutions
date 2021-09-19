package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoPlanPagoDTO;
public interface CoPlanPagoService {
    ListasDTO listadoCoPlanPago(String usuario, int cantidad, int origen) throws Exception;
    String crearCoPlanPago(CoPlanPagoDTO dto, String usuario) throws Exception;
}