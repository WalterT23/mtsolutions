package proyecto2.mtsolutions.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.FuncionalidadDTO;
import proyecto2.mtsolutions.dto.RolDTO;
import proyecto2.mtsolutions.dto.UsuarioDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface UsuarioDAO {
    UsuarioDTO userByName(@Param("username") String username);
    List<RolDTO> rolesByUserId(@Param("username") String username);
    List<UsuarioDTO> getUsuariosList(@Param("cantidad") Integer cantidadRegistro,

                                            @Param("offset") Integer offset);
    BigDecimal getUsuariosListCount();
    List<UsuarioDTO> buscarUsuariosList(@Param("filtro") FiltroDTO filtro,
                                               @Param("cantidad") Integer cantidadRegistro,
                                               @Param("offset") Integer offset);
    BigDecimal buscarUsuariosListCount(@Param("filtro") FiltroDTO filtro);
    int crearUsuario(@Param("dto")UsuarioDTO dto, @Param("user") String user, @Param("pass") String pass);
    List<RolDTO> getRoles();
    List<FuncionalidadDTO> getFuncionalidadesList();
    int insertRolUser(UsuarioDTO user);
    //List<PerfilDetalleDTO> getPerfilesList();


    //int updatePass(@Param("newPass") String newPass, @Param("idUsuario") String idUsuario, @Param("historial") String historial);
    //UsuarioDTO userById(String idUser);
    //EntidadUserDTO entidadById(String idEntidad);
    //List<EntidadUserDTO> entidadAsociada(String idUsuario);
    //EmpresaDTO getData(String dato);
}
