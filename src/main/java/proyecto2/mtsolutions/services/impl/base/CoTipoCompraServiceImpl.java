package proyecto2.mtsolutions.services.impl.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto2.mtsolutions.dao.base.CoTipoCompraDAO;
import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoTipoCompraDTO;
import proyecto2.mtsolutions.exceptions.ErrorCode;
import proyecto2.mtsolutions.exceptions.ServiceException;
import proyecto2.mtsolutions.services.base.CoTipoCompraService;
import proyecto2.mtsolutions.utils.CommonUtils;

import java.math.BigDecimal;

@Slf4j
@Service
public class CoTipoCompraServiceImpl implements CoTipoCompraService {
    @Autowired
    private CommonUtils ctrl;

    @Autowired
    private CoTipoCompraDAO coTipoCompraDAO;

    @Override
    public ListasDTO listadoCoTipoCompra(String usuario, int cantidad, int origen) throws Exception {
        try {
            ListasDTO lista = new ListasDTO();
            lista.setLista(coTipoCompraDAO.getCoTipoCompraList(cantidad, origen));
            lista.setTotalRegistros(coTipoCompraDAO.getCoTipoCompraListCount());
            lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            return lista;
        }catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    @Override
    public String crearCoTipoCompra(CoTipoCompraDTO dto, String usuario) throws Exception {
        try {
            String re = "Registro creado correctamente";
            int xc = coTipoCompraDAO.insertCoTipoCompra(dto);
            if (xc==0) {
                re = "No se pudo insertar el registro";
            }
            return re;
        } catch (ServiceException e) {
          throw new ServiceException(e.getErrorCode());
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }
}