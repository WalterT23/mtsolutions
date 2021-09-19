package proyecto2.mtsolutions.dto.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import proyecto2.mtsolutions.dto.AtributosBasicos;
import java.util.Date;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SegUsuarioDTO {

	private String cod;

	private String nombre;

	private String apellido;

	private String nroDocumento;

	private String direccion;

	private String estado;

	private String telefono;

	private String password;

	private Date fechaNacimiento;

	private Boolean resetPassword;

	private Date fechaCreacion;

	private Date fechaModificacion;

	private String usuarioCreacion;

	private String historialPassword;

	private String correo;

	private String datosAdicionales;



}