package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.StkArticuloDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface StkArticuloDAO {

    List<StkArticuloDTO> getStkArticuloList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getStkArticuloListCount();
    int insertStkArticulo(StkArticuloDTO dto);
}