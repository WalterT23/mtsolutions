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
public class CoComprasDetalleDTO {

	private Integer nroItem;

	private Integer nroFactura;

	private Integer cantidad;

	private BigDecimal precio;

	private BigDecimal descuento;

	private BigDecimal montoIva;

	private BigDecimal montoTotal;

	private Integer codArticulo;



}