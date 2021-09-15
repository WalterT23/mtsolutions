package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoComprasDetalleDTO;
public interface CoComprasDetalleService {
    ListasDTO listadoCoComprasDetalle(String usuario, int cantidad, int origen) throws Exception;
    String crearCoComprasDetalle(CoComprasDetalleDTO dto, String usuario) throws Exception;
}