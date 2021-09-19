package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.CoOrdenPagoDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CoOrdenPagoDAO {

    List<CoOrdenPagoDTO> getCoOrdenPagoList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getCoOrdenPagoListCount();
    int insertCoOrdenPago(CoOrdenPagoDTO dto);
}