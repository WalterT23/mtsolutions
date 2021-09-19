package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.StkListaPrecioDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface StkListaPrecioDAO {

    List<StkListaPrecioDTO> getStkListaPrecioList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getStkListaPrecioListCount();
    int insertStkListaPrecio(StkListaPrecioDTO dto);
}