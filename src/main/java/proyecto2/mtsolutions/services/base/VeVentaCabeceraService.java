package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.VeVentaCabeceraDTO;
public interface VeVentaCabeceraService {
    ListasDTO listadoVeVentaCabecera(String usuario, int cantidad, int origen) throws Exception;
    String crearVeVentaCabecera(VeVentaCabeceraDTO dto, String usuario) throws Exception;
}