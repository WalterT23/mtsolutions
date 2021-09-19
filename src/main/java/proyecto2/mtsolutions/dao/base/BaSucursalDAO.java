package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.BaSucursalDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface BaSucursalDAO {

    List<BaSucursalDTO> getBaSucursalList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getBaSucursalListCount();
    int insertBaSucursal(BaSucursalDTO dto);
}