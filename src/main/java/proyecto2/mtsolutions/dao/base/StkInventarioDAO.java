package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.StkInventarioDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface StkInventarioDAO {

    List<StkInventarioDTO> getStkInventarioList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getStkInventarioListCount();
    int insertStkInventario(StkInventarioDTO dto);
}