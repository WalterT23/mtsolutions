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
public class CoOrdenPagoDTO {

	private Integer idOp;

	private String descripcion;

	private Date fechaPago;

	private String usuarioCreacion;

	private String usuarioModificacion;

	private Date fechaModificacion;

	private Date fechaCreacion;

	private String motivoPago;

	private BigDecimal montoTotal;

	private Integer idProveedor;

	private Integer idFormaPago;

	private Integer idTipoPago;



}