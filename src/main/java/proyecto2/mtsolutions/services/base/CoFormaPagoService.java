package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoFormaPagoDTO;
public interface CoFormaPagoService {
    ListasDTO listadoCoFormaPago(String usuario, int cantidad, int origen) throws Exception;
    String crearCoFormaPago(CoFormaPagoDTO dto, String usuario) throws Exception;
}