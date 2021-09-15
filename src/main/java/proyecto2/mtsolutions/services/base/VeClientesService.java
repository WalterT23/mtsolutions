package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.VeClientesDTO;
public interface VeClientesService {
    ListasDTO listadoVeClientes(String usuario, int cantidad, int origen) throws Exception;
    String crearVeClientes(VeClientesDTO dto, String usuario) throws Exception;
}