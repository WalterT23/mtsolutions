package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.StkFamiliaDTO;
import proyecto2.mtsolutions.dto.base.StkMarcaDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface BaImpuestoDAO {
    List<StkMarcaDTO> getLista(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getListCount();
    StkFamiliaDTO getById(Integer idFamilia);
}
