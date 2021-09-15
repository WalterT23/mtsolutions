package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.CoOrdenCompraDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CoOrdenCompraDAO {

    List<CoOrdenCompraDTO> getCoOrdenCompraList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getCoOrdenCompraListCount();
    int insertCoOrdenCompra(CoOrdenCompraDTO dto);
}