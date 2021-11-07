package proyecto2.mtsolutions.services.impl.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto2.mtsolutions.dao.base.CoProveedorDAO;
import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.UsuarioDTO;
import proyecto2.mtsolutions.dto.base.CoProveedorDTO;
import proyecto2.mtsolutions.exceptions.ErrorCode;
import proyecto2.mtsolutions.exceptions.ServiceException;
import proyecto2.mtsolutions.services.base.CoProveedorService;
import proyecto2.mtsolutions.utils.CommonUtils;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
public class CoProveedorServiceImpl implements CoProveedorService {
    @Autowired
    private CommonUtils ctrl;

    @Autowired
    private CoProveedorDAO coProveedorDAO;

    @Override
    public ListasDTO listadoCoProveedor(String usuario, int cantidad, int origen) throws Exception {
        try {
            ListasDTO lista = new ListasDTO();
            lista.setLista(coProveedorDAO.getCoProveedorList(cantidad, origen));
            lista.setTotalRegistros(coProveedorDAO.getCoProveedorListCount());
            lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            return lista;
        }catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    @Override
    public String crearCoProveedor(CoProveedorDTO dto, String usuario) throws Exception {
        try {
            String re = "";
            if (ctrl.isNull(dto.getIdProveedor())) {
                re = "Registro creado correctamente";
                dto.setUsuarioCreacion(usuario);
                dto.setFechaCreacion(new Date());
                dto.setEstado(true);
                int xc = coProveedorDAO.insertCoProveedor(dto);
                if (xc == 0) {
                    re = "No se pudo insertar el registro";
                }
            } else {
                re = "Datos actualizados correctamente";
                int x = coProveedorDAO.updateProveedor(dto, usuario);
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
    public ListasDTO buscarProveedor(FiltroDTO filtro, String usuario, int cantidad, int origen) throws Exception {
        try {
            log.info(getClass().toString());
            log.info("Proveedor conectado: "+usuario);
            log.info("Servico: buscarProveedor --> filtro: "+filtro.toString()+" --> cantidad: "+cantidad+" --> origen: "+origen);
            ListasDTO lista = new ListasDTO();
            if (ctrl.isNull(filtro) || ctrl.isNull(filtro.getValor())) {
                throw new ServiceException(ErrorCode.NO_BODY_DATA);
            }
            lista.setLista(coProveedorDAO.buscarProveedorList(filtro, cantidad, origen));
            lista.setTotalRegistros(coProveedorDAO.buscarProveedorListCount(filtro));
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
    public CoProveedorDTO obtenerProveedor(CoProveedorDTO dto, String usuario) throws Exception {
        try {
            log.info(getClass().toString());
            log.info("Usuario conectado: "+usuario);
            log.info("Servicio: obtenerProveedor ");
            if (ctrl.isNull(dto)) {
                log.error(ErrorCode.NO_BODY_DATA.toString());
                throw new ServiceException(ErrorCode.NO_BODY_DATA);
            }
            if (ctrl.isNull(dto.getIdProveedor())) {
                log.error(ErrorCode.PARAMETROS_FALTANTES.toString());
                throw new ServiceException(ErrorCode.PARAMETROS_FALTANTES);
            }
            CoProveedorDTO exi = coProveedorDAO.getCoProveedorById(dto.getIdProveedor());
            return exi;
        } catch (ServiceException e) {
            throw new ServiceException(e.getErrorCode());
        } catch (Exception e) {
            log.error(e.toString());
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }
}