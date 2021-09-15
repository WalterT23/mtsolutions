package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.BaMonedaDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface BaMonedaDAO {

    List<BaMonedaDTO> getBaMonedaList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getBaMonedaListCount();
    int insertBaMoneda(BaMonedaDTO dto);
}