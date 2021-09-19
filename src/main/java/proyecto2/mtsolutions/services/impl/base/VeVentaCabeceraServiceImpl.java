package proyecto2.mtsolutions.services.impl.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto2.mtsolutions.dao.base.VeVentaCabeceraDAO;
import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.VeVentaCabeceraDTO;
import proyecto2.mtsolutions.exceptions.ErrorCode;
import proyecto2.mtsolutions.exceptions.ServiceException;
import proyecto2.mtsolutions.services.base.VeVentaCabeceraService;
import proyecto2.mtsolutions.utils.CommonUtils;

import java.math.BigDecimal;

@Slf4j
@Service
public class VeVentaCabeceraServiceImpl implements VeVentaCabeceraService {
    @Autowired
    private CommonUtils ctrl;

    @Autowired
    private VeVentaCabeceraDAO veVentaCabeceraDAO;

    @Override
    public ListasDTO listadoVeVentaCabecera(String usuario, int cantidad, int origen) throws Exception {
        try {
            ListasDTO lista = new ListasDTO();
            lista.setLista(veVentaCabeceraDAO.getVeVentaCabeceraList(cantidad, origen));
            lista.setTotalRegistros(veVentaCabeceraDAO.getVeVentaCabeceraListCount());
            lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            return lista;
        }catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    @Override
    public String crearVeVentaCabecera(VeVentaCabeceraDTO dto, String usuario) throws Exception {
        try {
            String re = "Registro creado correctamente";
            int xc = veVentaCabeceraDAO.insertVeVentaCabecera(dto);
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