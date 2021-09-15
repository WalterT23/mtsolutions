package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.StkRemisionesArticulosDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface StkRemisionesArticulosDAO {

    List<StkRemisionesArticulosDTO> getStkRemisionesArticulosList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getStkRemisionesArticulosListCount();
    int insertStkRemisionesArticulos(StkRemisionesArticulosDTO dto);
}