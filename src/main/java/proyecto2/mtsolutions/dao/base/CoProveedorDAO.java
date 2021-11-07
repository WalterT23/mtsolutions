package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.base.CoProveedorDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CoProveedorDAO {

    List<CoProveedorDTO> getCoProveedorList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getCoProveedorListCount();
    int insertCoProveedor(CoProveedorDTO dto);
    List<CoProveedorDTO> buscarProveedorList(@Param("filtro") FiltroDTO filtro,
                                        @Param("cantidad") Integer cantidadRegistro,
                                        @Param("offset") Integer offset);
    BigDecimal buscarProveedorListCount(@Param("filtro") FiltroDTO filtro);
    CoProveedorDTO getCoProveedorById(@Param("idProveedor") Integer id);
    int updateProveedor(@Param("dto") CoProveedorDTO dto, @Param("user") String user);

}