package proyecto2.mtsolutions.dto.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaImpuestoDTO {
    @NotBlank(message = "El impuesto es requerido")
    private Integer idImpuesto;
    private String descripcion;
    private Integer porcentaje;
    private Boolean activo;
}
