package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.BaMonedaDTO;
public interface BaMonedaService {
    ListasDTO listadoBaMoneda(String usuario, int cantidad, int origen) throws Exception;
    String crearBaMoneda(BaMonedaDTO dto, String usuario) throws Exception;
}