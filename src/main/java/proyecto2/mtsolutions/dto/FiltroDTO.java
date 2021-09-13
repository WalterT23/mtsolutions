package proyecto2.mtsolutions.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiltroDTO {
    private int id;
    private String valor;
    private String fechaDesde;
    private Date fechaDesdeDate;
    private String fechaHasta;
    private Date fechaHastaDate;
    private List<String> lista;
}
