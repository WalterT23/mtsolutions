package proyecto2.mtsolutions.services;

import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.UsuarioDTO;

public interface UsuarioService {
    ListasDTO listadoUsuarios(String usuario, int cantidad, int origen) throws Exception;
    ListasDTO buscarUsuario(FiltroDTO filtro, String usuario, int cantidad, int origen) throws Exception;
    ListasDTO listadoPerfiles(String usuario) throws Exception;
    ListasDTO listadoFuncionalidades(String usuario) throws Exception;
    String crearUsuario(UsuarioDTO dto, String usuario) throws Exception;
    ListasDTO listadoRoles(String usuario) throws Exception;
}
