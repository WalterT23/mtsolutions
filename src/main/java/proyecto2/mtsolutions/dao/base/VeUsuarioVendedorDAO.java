package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.VeUsuarioVendedorDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface VeUsuarioVendedorDAO {

    List<VeUsuarioVendedorDTO> getVeUsuarioVendedorList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getVeUsuarioVendedorListCount();
    int insertVeUsuarioVendedor(VeUsuarioVendedorDTO dto);
}