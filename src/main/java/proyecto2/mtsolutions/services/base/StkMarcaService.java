package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.exceptions.ServiceException;

public interface StkMarcaService {
    ListasDTO listado(String usuario, int cantidad, int origen) throws ServiceException;
}
