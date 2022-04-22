package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.VeClientesDTO;
import proyecto2.mtsolutions.exceptions.ServiceException;

public interface VeClientesService {
    ListasDTO listado(String usuario, int cantidad, int origen) throws ServiceException;
    String crear(VeClientesDTO dto, String usuario) throws ServiceException;
    String update(VeClientesDTO dto, String usuario) throws ServiceException;
    VeClientesDTO get(VeClientesDTO dto, String usuario) throws ServiceException;
    ListasDTO buscar(FiltroDTO filtro, String usuario, int cantidad, int origen) throws ServiceException;
}