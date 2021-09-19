package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoOrdenCompraDetalleDTO;
public interface CoOrdenCompraDetalleService {
    ListasDTO listadoCoOrdenCompraDetalle(String usuario, int cantidad, int origen) throws Exception;
    String crearCoOrdenCompraDetalle(CoOrdenCompraDetalleDTO dto, String usuario) throws Exception;
}