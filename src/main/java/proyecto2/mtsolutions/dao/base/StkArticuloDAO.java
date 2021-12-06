package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.base.CoProveedorDTO;
import proyecto2.mtsolutions.dto.base.StkArticuloDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface StkArticuloDAO {

    List<StkArticuloDTO> getStkArticuloList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getStkArticuloListCount();
    int insertStkArticulo(StkArticuloDTO dto);
    List<StkArticuloDTO> buscarArticuloList(@Param("filtro") FiltroDTO filtro,
                                             @Param("cantidad") Integer cantidadRegistro,
                                             @Param("offset") Integer offset);
    BigDecimal buscarArticuloListCount(@Param("filtro") FiltroDTO filtro);
    CoProveedorDTO getArticuloById(@Param("id") Integer id);
    int updateArticulo(@Param("dto") StkArticuloDTO dto, @Param("user") String user);
}