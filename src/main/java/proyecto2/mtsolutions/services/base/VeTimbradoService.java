package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.VeTimbradoDTO;
public interface VeTimbradoService {
    ListasDTO listadoVeTimbrado(String usuario, int cantidad, int origen) throws Exception;
    String crearVeTimbrado(VeTimbradoDTO dto, String usuario) throws Exception;
}