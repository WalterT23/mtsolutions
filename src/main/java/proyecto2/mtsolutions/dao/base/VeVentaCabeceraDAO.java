package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.VeVentaCabeceraDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface VeVentaCabeceraDAO {

    List<VeVentaCabeceraDTO> getVeVentaCabeceraList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getVeVentaCabeceraListCount();
    int insertVeVentaCabecera(VeVentaCabeceraDTO dto);
}