package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.SegRolUsuarioDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface SegRolUsuarioDAO {

    List<SegRolUsuarioDTO> getSegRolUsuarioList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getSegRolUsuarioListCount();
    int insertSegRolUsuario(SegRolUsuarioDTO dto);
}