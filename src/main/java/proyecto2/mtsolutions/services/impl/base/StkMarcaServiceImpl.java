package proyecto2.mtsolutions.services.impl.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto2.mtsolutions.dao.base.StkCategoriaDAO;
import proyecto2.mtsolutions.dao.base.StkMarcaDAO;
import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.exceptions.ErrorCode;
import proyecto2.mtsolutions.exceptions.ServiceException;
import proyecto2.mtsolutions.services.base.StkMarcaService;
import proyecto2.mtsolutions.utils.CommonUtils;

import java.math.BigDecimal;

@Service
public class StkMarcaServiceImpl implements StkMarcaService {

    @Autowired
    private CommonUtils ctrl;

    @Autowired
    private StkMarcaDAO marcaDAO;

    @Override
    public ListasDTO listado(String usuario, int cantidad, int origen) throws ServiceException {
        try {
            ListasDTO lista = new ListasDTO();
            lista.setLista(marcaDAO.getLista(cantidad, origen));
            lista.setTotalRegistros(marcaDAO.getListCount());
            lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            return lista;
        }catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }
}
