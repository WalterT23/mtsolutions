package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.SegPermisoDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface SegPermisoDAO {

    List<SegPermisoDTO> getSegPermisoList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getSegPermisoListCount();
    int insertSegPermiso(SegPermisoDTO dto);
}