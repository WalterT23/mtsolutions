package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.SegRolPermisoDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface SegRolPermisoDAO {

    List<SegRolPermisoDTO> getSegRolPermisoList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getSegRolPermisoListCount();
    int insertSegRolPermiso(SegRolPermisoDTO dto);
}