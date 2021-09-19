package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoComprasCabeceraDTO;
public interface CoComprasCabeceraService {
    ListasDTO listadoCoComprasCabecera(String usuario, int cantidad, int origen) throws Exception;
    String crearCoComprasCabecera(CoComprasCabeceraDTO dto, String usuario) throws Exception;
}