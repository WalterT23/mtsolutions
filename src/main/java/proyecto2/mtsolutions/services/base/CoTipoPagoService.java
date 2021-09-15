package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoTipoPagoDTO;
public interface CoTipoPagoService {
    ListasDTO listadoCoTipoPago(String usuario, int cantidad, int origen) throws Exception;
    String crearCoTipoPago(CoTipoPagoDTO dto, String usuario) throws Exception;
}