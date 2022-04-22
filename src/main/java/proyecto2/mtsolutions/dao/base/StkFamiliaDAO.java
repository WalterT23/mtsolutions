package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.base.StkCategoriaDTO;
import proyecto2.mtsolutions.dto.base.StkFamiliaDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface StkFamiliaDAO {
    List<StkFamiliaDTO> getLista(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getListCount();
    int insert(StkFamiliaDTO dto);
    int update(StkFamiliaDTO dto);
    StkFamiliaDTO getById(Integer idFamilia);
    List<StkFamiliaDTO> buscar(
            @Param("filtro") FiltroDTO filtro,
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    Boolean yaExiste(StkFamiliaDTO dto);
}
