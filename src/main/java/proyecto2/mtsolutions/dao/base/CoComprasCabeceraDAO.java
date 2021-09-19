package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.CoComprasCabeceraDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CoComprasCabeceraDAO {

    List<CoComprasCabeceraDTO> getCoComprasCabeceraList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getCoComprasCabeceraListCount();
    int insertCoComprasCabecera(CoComprasCabeceraDTO dto);
}