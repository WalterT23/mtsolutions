package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.CoOrdenCompraDetalleDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CoOrdenCompraDetalleDAO {

    List<CoOrdenCompraDetalleDTO> getCoOrdenCompraDetalleList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getCoOrdenCompraDetalleListCount();
    int insertCoOrdenCompraDetalle(CoOrdenCompraDetalleDTO dto);
}