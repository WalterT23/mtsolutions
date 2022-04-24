package proyecto2.mtsolutions.dto.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import proyecto2.mtsolutions.dto.AtributosBasicos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StkArticuloDTO {

	private Integer codArticulo;

	private List<StkListaPrecioDTO> listaPrecio; //este no se que hace aca

	@NotBlank(message = "La descripción es requerida")
	private String descripcion;

	/*private BigDecimal costoUnitario;

	private BigDecimal ultimoCosto;

	private BigDecimal precioVenta;*/

	@NotNull(message = "La categoría es requerida")
	private StkCategoriaDTO categoria;//entidad

	@NotNull(message = "La marca es requerida")
	private StkMarcaDTO marca;

	@NotNull(message = "La XXXX es requerida")
	private StkFamiliaDTO familia;

	@NotNull(message = "El impuesto es requerido")
	private BaImpuestoDTO impuesto;

	private Boolean activo;
}