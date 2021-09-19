package proyecto2.mtsolutions.services.impl.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto2.mtsolutions.dao.base.CoOrdenPagoDetalleDAO;
import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoOrdenPagoDetalleDTO;
import proyecto2.mtsolutions.exceptions.ErrorCode;
import proyecto2.mtsolutions.exceptions.ServiceException;
import proyecto2.mtsolutions.services.base.CoOrdenPagoDetalleService;
import proyecto2.mtsolutions.utils.CommonUtils;

import java.math.BigDecimal;

@Slf4j
@Service
public class CoOrdenPagoDetalleServiceImpl implements CoOrdenPagoDetalleService {
    @Autowired
    private CommonUtils ctrl;

    @Autowired
    private CoOrdenPagoDetalleDAO coOrdenPagoDetalleDAO;

    @Override
    public ListasDTO listadoCoOrdenPagoDetalle(String usuario, int cantidad, int origen) throws Exception {
        try {
            ListasDTO lista = new ListasDTO();
            lista.setLista(coOrdenPagoDetalleDAO.getCoOrdenPagoDetalleList(cantidad, origen));
            lista.setTotalRegistros(coOrdenPagoDetalleDAO.getCoOrdenPagoDetalleListCount());
            lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            return lista;
        }catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    @Override
    public String crearCoOrdenPagoDetalle(CoOrdenPagoDetalleDTO dto, String usuario) throws Exception {
        try {
            String re = "Registro creado correctamente";
            int xc = coOrdenPagoDetalleDAO.insertCoOrdenPagoDetalle(dto);
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