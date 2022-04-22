package proyecto2.mtsolutions.services.impl.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto2.mtsolutions.dao.base.BaImpuestoDAO;
import proyecto2.mtsolutions.dao.base.StkFamiliaDAO;
import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.StkFamiliaDTO;
import proyecto2.mtsolutions.dto.base.VeClientesDTO;
import proyecto2.mtsolutions.exceptions.ErrorCode;
import proyecto2.mtsolutions.exceptions.ServiceException;
import proyecto2.mtsolutions.services.base.StkFamiliaService;
import proyecto2.mtsolutions.utils.CommonUtils;

import java.math.BigDecimal;

@Service
public class StkFamiliaServiceImpl implements StkFamiliaService {

    @Autowired
    private CommonUtils ctrl;

    @Autowired
    private StkFamiliaDAO familiaDAO;

    @Override
    public ListasDTO listado(String usuario, int cantidad, int origen) throws ServiceException {
        try {
            ListasDTO lista = new ListasDTO();
            lista.setLista(familiaDAO.getLista(cantidad, origen));
            lista.setTotalRegistros(familiaDAO.getListCount());
            lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            return lista;
        }catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    @Override
    public String crear(StkFamiliaDTO dto, String usuario) throws ServiceException {
        return null;
    }

    @Override
    public String update(StkFamiliaDTO dto, String usuario) throws ServiceException {
        return null;
    }

    @Override
    public VeClientesDTO get(StkFamiliaDTO dto, String usuario) throws ServiceException {
        return null;
    }

    @Override
    public ListasDTO buscar(FiltroDTO filtro, String usuario, int cantidad, int origen) throws ServiceException {
        return null;
    }
}
