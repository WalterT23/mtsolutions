package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.CoFormaPagoDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CoFormaPagoDAO {

    List<CoFormaPagoDTO> getCoFormaPagoList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getCoFormaPagoListCount();
    int insertCoFormaPago(CoFormaPagoDTO dto);
}