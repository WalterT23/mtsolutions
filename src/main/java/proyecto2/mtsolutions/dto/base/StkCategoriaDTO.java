package proyecto2.mtsolutions.dto.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import proyecto2.mtsolutions.dto.AtributosBasicos;

import javax.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StkCategoriaDTO {

	@NotBlank(message = "La categoria es requerida")
	private String categoria;

	private String descripcion;

	private Boolean activo;

}