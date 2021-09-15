package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.VeVentaDetalleDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface VeVentaDetalleDAO {

    List<VeVentaDetalleDTO> getVeVentaDetalleList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getVeVentaDetalleListCount();
    int insertVeVentaDetalle(VeVentaDetalleDTO dto);
}