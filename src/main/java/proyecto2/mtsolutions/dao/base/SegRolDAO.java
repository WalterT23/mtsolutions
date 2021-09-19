package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.SegRolDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface SegRolDAO {

    List<SegRolDTO> getSegRolList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getSegRolListCount();
    int insertSegRol(SegRolDTO dto);
}