package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoTipoGastoDTO;
public interface CoTipoGastoService {
    ListasDTO listadoCoTipoGasto(String usuario, int cantidad, int origen) throws Exception;
    String crearCoTipoGasto(CoTipoGastoDTO dto, String usuario) throws Exception;
}