package proyecto2.mtsolutions.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import proyecto2.mtsolutions.dto.base.SegCoreTokensDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface SegCoreTokensDAO {

    List<SegCoreTokensDTO> getSegCoreTokensList(
            @Param("cantidad") Integer cantidadRegistro,
            @Param("offset") Integer offset
    );
    BigDecimal getSegCoreTokensListCount();
    int insertSegCoreTokens(SegCoreTokensDTO dto);
}