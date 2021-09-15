package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.SegUsuarioDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface SegUsuarioDAO {

    List<SegUsuarioDTO> getSegUsuarioList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getSegUsuarioListCount();
    int insertSegUsuario(SegUsuarioDTO dto);
}