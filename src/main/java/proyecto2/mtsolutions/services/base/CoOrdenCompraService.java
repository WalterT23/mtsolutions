package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoOrdenCompraDTO;
public interface CoOrdenCompraService {
    ListasDTO listadoCoOrdenCompra(String usuario, int cantidad, int origen) throws Exception;
    String crearCoOrdenCompra(CoOrdenCompraDTO dto, String usuario) throws Exception;
}