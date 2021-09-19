package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.SegFuncionalidadPermisoDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface SegFuncionalidadPermisoDAO {

    List<SegFuncionalidadPermisoDTO> getSegFuncionalidadPermisoList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getSegFuncionalidadPermisoListCount();
    int insertSegFuncionalidadPermiso(SegFuncionalidadPermisoDTO dto);
}