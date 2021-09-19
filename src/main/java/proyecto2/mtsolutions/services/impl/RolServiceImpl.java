package proyecto2.mtsolutions.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import proyecto2.mtsolutions.configuration.Constantes;
import proyecto2.mtsolutions.dao.RolDAO;
import proyecto2.mtsolutions.dto.PerfilDTO;
import proyecto2.mtsolutions.dto.RolDTO;
import proyecto2.mtsolutions.dto.RolUpdateDTO;
import proyecto2.mtsolutions.exceptions.ErrorCode;
import proyecto2.mtsolutions.exceptions.ServiceException;
import proyecto2.mtsolutions.services.RolService;
import proyecto2.mtsolutions.utils.CommonUtils;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {
    final CommonUtils ctrl;
    final RolDAO rolDAO;

    @Override
    public String actualizarRol(RolUpdateDTO dto, String user) {
        try {
            String re = "Actualizado correctamente";
            log.info(getClass().toString());
            log.info("Usuario conectado: " + user);
            log.info("Servicio: actualizarRol ");
            if (dto.isActivo()) {
                rolDAO.insertarAsociacion(dto);
            } else {
                rolDAO.eliminarAsociacion(dto);
            }
            return re;
        } catch (ServiceException e) {
            throw new ServiceException(e.getErrorCode());
        } catch (Exception e) {
            log.error(e.toString());
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    @Override
    public String crearRol(RolDTO dto, String user) {
        try {
            String re = "Creado correctamente";
            log.info(getClass().toString());
            log.info("Usuario conectado: " + user);
            log.info("Servicio: crearRol ");
            if (ctrl.isNull(dto.getNombre())) {
                throw new ServiceException(ErrorCode.NO_TITULO_ROL);
            }
            if (ctrl.isNull(dto.getPerfiles())) {
                throw new ServiceException(ErrorCode.NO_PERFIL_LIST);
            }
            dto.setNombre(dto.getNombre()
                    .trim()
                    .toUpperCase());
            String codPerfil = Constantes.PERFIL_PREFIJO.concat(dto.getNombre()
                    .replaceAll(" ","_"));
            PerfilDTO perfil = new PerfilDTO();
            perfil.setId(codPerfil);
            perfil.setActivo(true);
            perfil.setNombre(dto.getNombre());
            perfil.setDescripcion(dto.getNombre());
            rolDAO.insertarPermiso(perfil);

            String codRol = Constantes.ROL_PREFIJO.concat(dto.getNombre()
                    .replaceAll(" ", "_"));
            dto.setId(codRol);
            dto.setActivo(true);
            rolDAO.insertarRol(dto);

            RolUpdateDTO up = new RolUpdateDTO();
            up.setIdPerfil(perfil.getId());
            up.setIdRol(dto.getId());
            rolDAO.insertarRolPerfil(up);

            dto.getPerfiles().forEach(func-> {
                if (func.isActivo()) {
                    RolUpdateDTO up2 = new RolUpdateDTO();
                    up2.setIdPerfil(perfil.getId());
                    up2.setIdFuncion(func.getId());
                    rolDAO.insertarAsociacion(up2);
                }
            });

            return re;
        } catch (ServiceException e) {
            throw new ServiceException(e.getErrorCode());
        } catch (Exception e) {
            log.error(e.toString());
            throw new ServiceException(ErrorCode.INTERNAL_ERROR);
        }
    }
}
