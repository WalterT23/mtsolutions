package proyecto2.mtsolutions.dto.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import proyecto2.mtsolutions.dto.AtributosBasicos;
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

	private String descripcion;

	/*private BigDecimal costoUnitario;

	private BigDecimal ultimoCosto;

	private BigDecimal precioVenta;*/

	private StkCategoriaDTO categoria;//entidad

	private StkMarcaDTO marca;

	private StkFamiliaDTO familia;

	private BaImpuestoDTO impuesto;

	private Boolean activo;
}