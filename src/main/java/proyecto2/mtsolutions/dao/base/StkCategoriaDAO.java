package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.StkCategoriaDTO;
import proyecto2.mtsolutions.dto.base.VeClientesDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface StkCategoriaDAO {

    List<StkCategoriaDTO> getStkCategoriaList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getStkCategoriaListCount();
    int insertStkCategoria(StkCategoriaDTO dto);
    StkCategoriaDTO getById(String categoria);
}