package proyecto2.mtsolutions.services.impl.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto2.mtsolutions.dao.base.StkArticuloDAO;
import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoProveedorDTO;
import proyecto2.mtsolutions.dto.base.StkArticuloDTO;
import proyecto2.mtsolutions.exceptions.ErrorCode;
import proyecto2.mtsolutions.exceptions.ServiceException;
import proyecto2.mtsolutions.services.base.StkArticuloService;
import proyecto2.mtsolutions.utils.CommonUtils;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
public class StkArticuloServiceImpl implements StkArticuloService {
    @Autowired
    private CommonUtils ctrl;

    @Autowired
    private StkArticuloDAO stkArticuloDAO;

    @Override
    public ListasDTO listadoStkArticulo(String usuario, int cantidad, int origen) throws Exception {
        try {
            ListasDTO lista = new ListasDTO();
            lista.setLista(stkArticuloDAO.getStkArticuloList(cantidad, origen));
            lista.setTotalRegistros(stkArticuloDAO.getStkArticuloListCount());
            lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            return lista;
        }catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    @Override
    public String crearStkArticulo(StkArticuloDTO dto, String usuario) throws Exception {
        try {
            String re = "";
            if (ctrl.isNull(dto.getIdProveedor())) {
                re = "Registro creado correctamente";
                int xc = stkArticuloDAO.insertStkArticulo(dto);
                if (xc == 0) {
                    re = "No se pudo insertar el registro";
                }
            } else {
                re = "Datos actualizados correctamente";
                int x = stkArticuloDAO.updateArticulo(dto, usuario);
                if (x == 0) {
                    re = "No actualizado";
                }
            }
            return re;
        } catch (ServiceException e) {
          throw new ServiceException(e.getErrorCode());
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    @Override
    public ListasDTO buscarArticulo(FiltroDTO filtro, String usuario, int cantidad, int origen) throws Exception {
        try {
            log.info(getClass().toString());
            log.info("Articulo conectado: "+usuario);
            log.info("Servico: buscarArticulo --> filtro: "+filtro.toString()+" --> cantidad: "+cantidad+" --> origen: "+origen);
            ListasDTO lista = new ListasDTO();
            if (ctrl.isNull(filtro) || ctrl.isNull(filtro.getValor())) {
                throw new ServiceException(ErrorCode.NO_BODY_DATA);
            }
            lista.setLista(stkArticuloDAO.buscarArticuloList(filtro, cantidad, origen));
            lista.setTotalRegistros(stkArticuloDAO.buscarArticuloListCount(filtro));
            lista.setCantActualRegistros(ctrl.isNull(lista.getLista())?BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            log.info("Datos: "+lista.toString());
            return lista;
        } catch( ServiceException e) {
            log.error(e.toString());
            throw new ServiceException(e.getErrorCode());
        }catch (Exception e) {
            log.error(e.toString());
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.getMessage());
        }
    }

    @Override
    public CoProveedorDTO obtenerArticulo(StkArticuloDTO dto, String usuario) throws Exception {
        try {
            log.info(getClass().toString());
            log.info("Usuario conectado: "+usuario);
            log.info("Servicio: obtenerArticulo ");
            if (ctrl.isNull(dto)) {
                log.error(ErrorCode.NO_BODY_DATA.toString());
                throw new ServiceException(ErrorCode.NO_BODY_DATA);
            }
            if (ctrl.isNull(dto.getIdProveedor())) {
                log.error(ErrorCode.PARAMETROS_FALTANTES.toString());
                throw new ServiceException(ErrorCode.PARAMETROS_FALTANTES);
            }
            CoProveedorDTO exi = stkArticuloDAO.getArticuloById(dto.getIdProveedor());
            return exi;
        } catch (ServiceException e) {
            throw new ServiceException(e.getErrorCode());
        } catch (Exception e) {
            log.error(e.toString());
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

}