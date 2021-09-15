package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoOrdenPagoDTO;
public interface CoOrdenPagoService {
    ListasDTO listadoCoOrdenPago(String usuario, int cantidad, int origen) throws Exception;
    String crearCoOrdenPago(CoOrdenPagoDTO dto, String usuario) throws Exception;
}