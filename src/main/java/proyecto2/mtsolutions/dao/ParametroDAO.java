package proyecto2.mtsolutions.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import proyecto2.mtsolutions.dto.BaseDTO;
import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.HelperDTO;
import proyecto2.mtsolutions.dto.ParametroDTO;

import java.util.List;

@Mapper
public interface ParametroDAO {

    @Select("select *" +
            "from conf.parameters p " +
            "where p.active = true " +
            "and p.key = #{parametro}")
    ParametroDTO getParametroByKey(String parametro);
    List<HelperDTO> traerHelpers();
    List<BaseDTO> traerEmpresas(FiltroDTO filtro);
    List<BaseDTO> traerSucursal(FiltroDTO filtro);
    List<BaseDTO> traerMonedas(FiltroDTO filtro);
    List<BaseDTO> traerImpuestos(FiltroDTO filtro);
    List<BaseDTO> traerCategorias(FiltroDTO filtro);
    List<BaseDTO> traerFamilias(FiltroDTO filtro);
    List<BaseDTO> traerTipoGastos(FiltroDTO filtro);

}
