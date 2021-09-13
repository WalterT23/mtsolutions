package proyecto2.mtsolutions.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import proyecto2.mtsolutions.dto.ParametroDTO;

@Mapper
public interface ParametroDAO {

    @Select("select *" +
            "from conf.parameters p " +
            "where p.active = true " +
            "and p.key = #{parametro}")
    ParametroDTO getParametroByKey(String parametro);
}
