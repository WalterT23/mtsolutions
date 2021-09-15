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
public class CoComprasCabeceraDTO {

	private Integer nroFactura;

	private BigDecimal ruc;

	private Date fechaEmision;

	private String condicion;

	private BigDecimal totalIva;

	private BigDecimal totalGeneral;

	private String ubicacion;

	private Integer idTipoCompra;

	private Integer idTipoCobro;

	private Integer idProveedor;

	private Integer idOc;

	private String usuarioCreacion;

	private Date fechaCreacion;

	private String usuarioModificacion;

	private Date fechaModificacion;



}