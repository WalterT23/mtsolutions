package proyecto2.mtsolutions.services.impl.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto2.mtsolutions.dao.base.BaImpuestoDAO;
import proyecto2.mtsolutions.dao.base.StkMarcaDAO;
import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.exceptions.ErrorCode;
import proyecto2.mtsolutions.exceptions.ServiceException;
import proyecto2.mtsolutions.services.base.BaImpuestoService;
import proyecto2.mtsolutions.utils.CommonUtils;

import java.math.BigDecimal;

@Service
public class BaImpuestoServiceImpl implements BaImpuestoService {

    @Autowired
    private CommonUtils ctrl;

    @Autowired
    private BaImpuestoDAO impuestoDAO;

    @Override
    public ListasDTO listado(String usuario, int cantidad, int origen) throws ServiceException {
        try {
            ListasDTO lista = new ListasDTO();
            lista.setLista(impuestoDAO.getLista(cantidad, origen));
            lista.setTotalRegistros(impuestoDAO.getListCount());
            lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            return lista;
        }catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }
}
