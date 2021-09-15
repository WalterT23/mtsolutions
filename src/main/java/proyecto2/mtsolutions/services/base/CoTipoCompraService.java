package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoTipoCompraDTO;
public interface CoTipoCompraService {
    ListasDTO listadoCoTipoCompra(String usuario, int cantidad, int origen) throws Exception;
    String crearCoTipoCompra(CoTipoCompraDTO dto, String usuario) throws Exception;
}