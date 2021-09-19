package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.CoBancoDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CoBancoDAO {

    List<CoBancoDTO> getCoBancoList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getCoBancoListCount();
    int insertCoBanco(CoBancoDTO dto);
}