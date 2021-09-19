package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.CoTipoCompraDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CoTipoCompraDAO {

    List<CoTipoCompraDTO> getCoTipoCompraList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getCoTipoCompraListCount();
    int insertCoTipoCompra(CoTipoCompraDTO dto);
}