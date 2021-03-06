package proyecto2.mtsolutions.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import proyecto2.mtsolutions.dao.UsuarioDAO;
import proyecto2.mtsolutions.dto.*;
import proyecto2.mtsolutions.exceptions.ErrorCode;
import proyecto2.mtsolutions.exceptions.ServiceException;
import proyecto2.mtsolutions.services.UsuarioService;
import proyecto2.mtsolutions.utils.CommonUtils;

import java.math.BigDecimal;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {
    private static final int BCRYPT_COMPLEXITY = 6;
    @Autowired
    private CommonUtils ctrl;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public ListasDTO listadoUsuarios(String usuario, int cantidad, int origen) throws Exception {
        try {
            log.info(getClass().toString());
            log.info("Usuario conectado: "+usuario);
            log.info("Servico: listadoUsuarios --> cantidad: "+cantidad+" --> origen: "+origen);
            ListasDTO lista = new ListasDTO();
            lista.setLista(usuarioDAO.getUsuariosList(cantidad, origen));
            lista.setTotalRegistros(usuarioDAO.getUsuariosListCount());
            lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            log.info("Datos: "+lista.toString());
            return lista;
        }catch (Exception e) {
            log.error(e.toString());
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    @Override
    public ListasDTO buscarUsuario(FiltroDTO filtro, String usuario, int cantidad, int origen) throws Exception {
        try {
            log.info(getClass().toString());
            log.info("Usuario conectado: "+usuario);
            log.info("Servico: buscarUsuario --> filtro: "+filtro.toString()+" --> cantidad: "+cantidad+" --> origen: "+origen);
            ListasDTO lista = new ListasDTO();
            if (ctrl.isNull(filtro) || ctrl.isNull(filtro.getValor())) {
                throw new ServiceException(ErrorCode.NO_BODY_DATA);
            }
            lista.setLista(usuarioDAO.buscarUsuariosList(filtro, cantidad, origen));
            lista.setTotalRegistros(usuarioDAO.buscarUsuariosListCount(filtro));
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
    public ListasDTO listadoPerfiles(String usuario) throws Exception {
        /*try {
            log.info(getClass().toString());
            log.info("Usuario conectado: "+usuario);
            log.info("Servicio: listadoPerfiles ");
            ListasDTO lista = new ListasDTO();
            lista.setLista(usuarioDAO.getPerfilesList());
            lista.setTotalRegistros(ctrl.isNull(lista.getLista())?BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            lista.setCantActualRegistros(ctrl.isNull(lista.getLista())?BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            //log.info("Datos: "+lista.toString());
            List<String> listaFuncionalidades;
            log.info("Iteracion de la lista de funcionalidades obtenida");
            for (Object dato:  lista.getLista()) {
                listaFuncionalidades = new ArrayList<>();
                PerfilDetalleDTO perfil = (PerfilDetalleDTO) dato;
                JsonObject jsonObject = JsonParser.parseString(perfil.getFuncionalidades()).getAsJsonObject();
                Set<String> da = JsonParser.parseString(perfil.getFuncionalidades()).getAsJsonObject().keySet();
                for (String key : da) {
                    String p = jsonObject.get(key).getAsString();
                    listaFuncionalidades.add(p);
                }
                ((PerfilDetalleDTO) dato).setListaFuncionalidades(listaFuncionalidades);
                ((PerfilDetalleDTO) dato).setFuncionalidades(null);
            }
            return lista;
        }catch (Exception e) {
            log.error(e.toString());
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }*/
        return null;
    }

    @Override
    public ListasDTO listadoRoles(String usuario) throws Exception {
        try {
            log.info(getClass().toString());
            log.info("Usuario conectado: "+usuario);
            log.info("Servicio: listadoRoles ");
            ListasDTO lista = new ListasDTO();
            lista.setLista(usuarioDAO.getRoles());
            lista.setTotalRegistros(ctrl.isNull(lista.getLista())?BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            lista.setCantActualRegistros(ctrl.isNull(lista.getLista())?BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            return lista;
        }catch (Exception e) {
            log.error(e.toString());
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    @Override
    public ListasDTO listadoFuncionalidades(String usuario) throws Exception {
        try {
            log.info(getClass().toString());
            log.info("Usuario conectado: "+usuario);
            log.info("Servicio: listadoFuncionalidades ");
            ListasDTO lista = new ListasDTO();
            lista.setLista(usuarioDAO.getFuncionalidadesList());
            lista.setTotalRegistros(ctrl.isNull(lista.getLista())?BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            lista.setCantActualRegistros(ctrl.isNull(lista.getLista())?BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            return lista;
        }catch (Exception e) {
            log.error(e.toString());
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    @Override
    public String crearUsuario(UsuarioDTO dto, String usuario) throws Exception {
        try {
            String re = "Usuario creado correctamente";
            log.info(getClass().toString());
            log.info("Usuario conectado: "+usuario);
            log.info("Servicio: crearUsuario ");
            if (ctrl.isNull(dto)) {
                log.error(ErrorCode.NO_BODY_DATA.toString());
                throw new ServiceException(ErrorCode.NO_BODY_DATA);
            }
            if (ctrl.isNull(dto.getNombre()) || ctrl.isNull(dto.getApellido()) || ctrl.isNull(dto.getDocumento())
                    || ctrl.isNull(dto.getEmail()) || ctrl.isNull(dto.getDireccion()) || ctrl.isNull(dto.getRol())) {
                log.error(ErrorCode.PARAMETROS_FALTANTES.toString());
                throw new ServiceException(ErrorCode.PARAMETROS_FALTANTES);
            }
            if (ctrl.isNull(dto.getId())) {
                log.error(ErrorCode.ERROR_GENERAR_USUARIO.toString());
                throw new ServiceException(ErrorCode.ERROR_GENERAR_USUARIO);
            }
            UsuarioDTO exi = usuarioDAO.userByName(dto.getId());
            if (ctrl.isNull(exi)) {
                //String usr = ctrl.obtenerUsuario(dto.getNombre(), dto.getApellido());
                //dto.setId(dto.getUsername());
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(BCRYPT_COMPLEXITY);
                String newPass = passwordEncoder.encode(dto.getPass()==null?"123":dto.getPass());
                dto.setEstado("ACTIVO");
                usuarioDAO.crearUsuario(dto, usuario, newPass);
                if (ctrl.isNull(dto.getId())) {
                    re = "No creado";
                } else {
                    int xc = usuarioDAO.insertRolUser(dto);
                    if (xc==0) {
                        re = "Usuario creado pero no fue posible asignar un rol";
                    }
                }
            } else {
                re = "Datos actualizados correctamente";
                int x = usuarioDAO.updateUsuario(dto, usuario);
                if (x == 0) {
                    re = "No actualizado";
                } else {
                    int xc = usuarioDAO.updateRolUSer(dto);
                }
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
    public UsuarioDTO obtenerUsuario(UsuarioDTO dto, String usuario) throws Exception {
        try {
            log.info(getClass().toString());
            log.info("Usuario conectado: "+usuario);
            log.info("Servicio: obtenerUsuario ");
            if (ctrl.isNull(dto)) {
                log.error(ErrorCode.NO_BODY_DATA.toString());
                throw new ServiceException(ErrorCode.NO_BODY_DATA);
            }
            if (ctrl.isNull(dto.getId())) {
                log.error(ErrorCode.PARAMETROS_FALTANTES.toString());
                throw new ServiceException(ErrorCode.PARAMETROS_FALTANTES);
            }
            UsuarioDTO exi = usuarioDAO.userByNameView(dto.getId());
            exi.setRoles(usuarioDAO.rolesByUserId(exi.getId()));
            return exi;
        } catch (ServiceException e) {
            throw new ServiceException(e.getErrorCode());
        } catch (Exception e) {
            log.error(e.toString());
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    /**
     * Verifica que el username proveido no exista en la base
     * @param dto
     * @param usuario
     * @return
     * @throws Exception
     */
    @Override
    public int checkUser(UsuarioDTO dto, String usuario) throws Exception {
        try {
            log.info(getClass().toString());
            log.info("Usuario conectado: "+usuario);
            log.info("Servicio: checkUser ");
            if (ctrl.isNull(dto)) {
                log.error(ErrorCode.NO_BODY_DATA.toString());
                throw new ServiceException(ErrorCode.NO_BODY_DATA);
            }
            if (ctrl.isNull(dto.getUsername())) {
                log.error(ErrorCode.PARAMETROS_FALTANTES.toString());
                throw new ServiceException(ErrorCode.PARAMETROS_FALTANTES);
            }
            int exi = usuarioDAO.userByCheck(dto.getUsername());
            return exi;
        } catch (ServiceException e) {
            throw new ServiceException(e.getErrorCode());
        } catch (Exception e) {
            log.error(e.toString());
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }
}
