package proyecto2.mtsolutions.dto.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import proyecto2.mtsolutions.dto.AtributosBasicos;
import java.math.BigDecimal;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StkArticuloDTO {

	private Integer codArticulo;

	private BigDecimal idListaPrecio;

	private String descripcion;

	private BigDecimal costoUnitario;

	private String ubicacion;

	private BigDecimal ultimoCosto;

	private BigDecimal precioVenta;

	private String categoria;

	private Integer idProveedor;

	private String nombreProveedor;



}