package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.SegFuncionalidadDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface SegFuncionalidadDAO {

    List<SegFuncionalidadDTO> getSegFuncionalidadList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getSegFuncionalidadListCount();
    int insertSegFuncionalidad(SegFuncionalidadDTO dto);
}