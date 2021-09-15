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
public class VeVentaCabeceraDTO {

	private Integer nroFactura;

	private Long idTimbrado;

	private String usuarioCreacion;

	private Date fechaEmision;

	private String usuarioModificacion;

	private Date fechaCreacion;

	private Boolean contado;

	private Date fechaModificacion;

	private BigDecimal totalIva;

	private BigDecimal totalGeneral;

	private Integer codSucursal;

	private Integer codVendedor;

	private BigDecimal ruc;



}