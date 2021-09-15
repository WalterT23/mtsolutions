package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.SegCoreTokensDTO;
public interface SegCoreTokensService {
    ListasDTO listadoSegCoreTokens(String usuario, int cantidad, int origen) throws Exception;
    String crearSegCoreTokens(SegCoreTokensDTO dto, String usuario) throws Exception;
}