package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.StkListaPrecioDTO;
public interface StkListaPrecioService {
    ListasDTO listadoStkListaPrecio(String usuario, int cantidad, int origen) throws Exception;
    String crearStkListaPrecio(StkListaPrecioDTO dto, String usuario) throws Exception;
}