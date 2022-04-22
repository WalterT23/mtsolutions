package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.base.VeClientesDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface VeClientesDAO {

    List<VeClientesDTO> getLista(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getListCount();
    int insert(VeClientesDTO dto);
    int update(VeClientesDTO dto);
    VeClientesDTO getById(@Param("idCliente") Integer idCliente);
    List<VeClientesDTO> buscar(
            @Param("filtro") FiltroDTO filtro,
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    Boolean yaExiste(VeClientesDTO dto);
}