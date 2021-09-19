package proyecto2.mtsolutions.services.impl.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto2.mtsolutions.dao.base.BaSucursalDAO;
import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.BaSucursalDTO;
import proyecto2.mtsolutions.exceptions.ErrorCode;
import proyecto2.mtsolutions.exceptions.ServiceException;
import proyecto2.mtsolutions.services.base.BaSucursalService;
import proyecto2.mtsolutions.utils.CommonUtils;

import java.math.BigDecimal;

@Slf4j
@Service
public class BaSucursalServiceImpl implements BaSucursalService {
    @Autowired
    private CommonUtils ctrl;

    @Autowired
    private BaSucursalDAO baSucursalDAO;

    @Override
    public ListasDTO listadoBaSucursal(String usuario, int cantidad, int origen) throws Exception {
        try {
            ListasDTO lista = new ListasDTO();
            lista.setLista(baSucursalDAO.getBaSucursalList(cantidad, origen));
            lista.setTotalRegistros(baSucursalDAO.getBaSucursalListCount());
            lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            return lista;
        }catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    @Override
    public String crearBaSucursal(BaSucursalDTO dto, String usuario) throws Exception {
        try {
            String re = "Registro creado correctamente";
            int xc = baSucursalDAO.insertBaSucursal(dto);
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