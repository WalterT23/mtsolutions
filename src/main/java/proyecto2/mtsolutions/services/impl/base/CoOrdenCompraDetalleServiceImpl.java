package proyecto2.mtsolutions.services.impl.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto2.mtsolutions.dao.base.CoOrdenCompraDetalleDAO;
import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoOrdenCompraDetalleDTO;
import proyecto2.mtsolutions.exceptions.ErrorCode;
import proyecto2.mtsolutions.exceptions.ServiceException;
import proyecto2.mtsolutions.services.base.CoOrdenCompraDetalleService;
import proyecto2.mtsolutions.utils.CommonUtils;

import java.math.BigDecimal;

@Slf4j
@Service
public class CoOrdenCompraDetalleServiceImpl implements CoOrdenCompraDetalleService {
    @Autowired
    private CommonUtils ctrl;

    @Autowired
    private CoOrdenCompraDetalleDAO coOrdenCompraDetalleDAO;

    @Override
    public ListasDTO listadoCoOrdenCompraDetalle(String usuario, int cantidad, int origen) throws Exception {
        try {
            ListasDTO lista = new ListasDTO();
            lista.setLista(coOrdenCompraDetalleDAO.getCoOrdenCompraDetalleList(cantidad, origen));
            lista.setTotalRegistros(coOrdenCompraDetalleDAO.getCoOrdenCompraDetalleListCount());
            lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            return lista;
        }catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    @Override
    public String crearCoOrdenCompraDetalle(CoOrdenCompraDetalleDTO dto, String usuario) throws Exception {
        try {
            String re = "Registro creado correctamente";
            int xc = coOrdenCompraDetalleDAO.insertCoOrdenCompraDetalle(dto);
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