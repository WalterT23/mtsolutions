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
public class VeClientesDTO {

	private BigDecimal ruc;

	private BigDecimal dv;

	private String direccion;

	private String razonSocial;

	private String email;

	private Boolean esJuridico;

	private String telefono;

	private String usuarioCreacion;

	private String usuarioModificaicon;

	private Date fechaCreacion;

	private Date fechaModificacion;



}