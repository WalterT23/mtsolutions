package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.CoPlanPagoDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CoPlanPagoDAO {

    List<CoPlanPagoDTO> getCoPlanPagoList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getCoPlanPagoListCount();
    int insertCoPlanPago(CoPlanPagoDTO dto);
}