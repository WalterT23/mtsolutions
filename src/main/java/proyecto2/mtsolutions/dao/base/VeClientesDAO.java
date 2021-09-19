package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.VeClientesDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface VeClientesDAO {

    List<VeClientesDTO> getVeClientesList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getVeClientesListCount();
    int insertVeClientes(VeClientesDTO dto);
}