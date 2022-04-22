package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.StkFamiliaDTO;
import proyecto2.mtsolutions.dto.base.VeClientesDTO;
import proyecto2.mtsolutions.exceptions.ServiceException;

public interface StkFamiliaService {
    ListasDTO listado(String usuario, int cantidad, int origen) throws ServiceException;
    String crear(StkFamiliaDTO dto, String usuario) throws ServiceException;
    String update(StkFamiliaDTO dto, String usuario) throws ServiceException;
    VeClientesDTO get(StkFamiliaDTO dto, String usuario) throws ServiceException;
    ListasDTO buscar(FiltroDTO filtro, String usuario, int cantidad, int origen) throws ServiceException;
}
