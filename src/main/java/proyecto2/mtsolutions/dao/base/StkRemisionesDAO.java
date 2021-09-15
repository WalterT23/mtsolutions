package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.StkRemisionesDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface StkRemisionesDAO {

    List<StkRemisionesDTO> getStkRemisionesList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getStkRemisionesListCount();
    int insertStkRemisiones(StkRemisionesDTO dto);
}