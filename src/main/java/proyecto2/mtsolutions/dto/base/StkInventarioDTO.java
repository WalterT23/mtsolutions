package proyecto2.mtsolutions.dto.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import proyecto2.mtsolutions.dto.AtributosBasicos;
import java.util.Date;

import java.math.BigDecimal;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StkInventarioDTO {

	private Integer codSucursal;

	private Integer codArticulo;

	private BigDecimal stockMinimo;

	private String usuarioCreacion;

	private String usuaroMoificacion;

	private Date fechaCreacion;

	private Date fechaModificacion;

	private BigDecimal stockActual;

	private Date fecha;



}