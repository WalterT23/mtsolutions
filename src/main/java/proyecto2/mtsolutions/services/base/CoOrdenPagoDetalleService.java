package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoOrdenPagoDetalleDTO;
public interface CoOrdenPagoDetalleService {
    ListasDTO listadoCoOrdenPagoDetalle(String usuario, int cantidad, int origen) throws Exception;
    String crearCoOrdenPagoDetalle(CoOrdenPagoDetalleDTO dto, String usuario) throws Exception;
}