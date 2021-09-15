package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.StkAjusteInventarioDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface StkAjusteInventarioDAO {

    List<StkAjusteInventarioDTO> getStkAjusteInventarioList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getStkAjusteInventarioListCount();
    int insertStkAjusteInventario(StkAjusteInventarioDTO dto);
}