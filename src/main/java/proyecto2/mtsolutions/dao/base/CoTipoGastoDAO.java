package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.CoTipoGastoDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CoTipoGastoDAO {

    List<CoTipoGastoDTO> getCoTipoGastoList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getCoTipoGastoListCount();
    int insertCoTipoGasto(CoTipoGastoDTO dto);
}