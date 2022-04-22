package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.StkMarcaDTO;
import proyecto2.mtsolutions.dto.base.VeClientesDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface StkMarcaDAO {
    List<StkMarcaDTO> getLista(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getListCount();
    StkMarcaDTO getById(Integer id);
}
