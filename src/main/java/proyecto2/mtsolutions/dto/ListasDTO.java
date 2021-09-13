package proyecto2.mtsolutions.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListasDTO {
    private BigDecimal totalRegistros;
    private BigDecimal cantActualRegistros;
    private List<?> lista;
    private String filtroTipo;
    private List<?> listaTipos;
    private BigDecimal cantRegValidos;
    private BigDecimal cantRegErrores;
}
