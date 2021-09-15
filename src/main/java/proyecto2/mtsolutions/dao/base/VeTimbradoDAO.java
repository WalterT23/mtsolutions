package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.VeTimbradoDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface VeTimbradoDAO {

    List<VeTimbradoDTO> getVeTimbradoList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getVeTimbradoListCount();
    int insertVeTimbrado(VeTimbradoDTO dto);
}