package proyecto2.mtsolutions.dao;

import org.apache.ibatis.annotations.Mapper;
import proyecto2.mtsolutions.dto.PerfilDTO;
import proyecto2.mtsolutions.dto.RolDTO;
import proyecto2.mtsolutions.dto.RolUpdateDTO;

@Mapper
public interface RolDAO {
    int eliminarAsociacion(RolUpdateDTO dto);
    int insertarAsociacion(RolUpdateDTO dto);
    int insertarPermiso(PerfilDTO perfil);
    int insertarRol(RolDTO rol);
    int insertarRolPerfil(RolUpdateDTO dto);
}
