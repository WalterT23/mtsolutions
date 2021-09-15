package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.BaSucursalDTO;
public interface BaSucursalService {
    ListasDTO listadoBaSucursal(String usuario, int cantidad, int origen) throws Exception;
    String crearBaSucursal(BaSucursalDTO dto, String usuario) throws Exception;
}