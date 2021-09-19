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
public class BaSucursalDTO {

	private Integer codSucursal;

	private String nombre;

	private String direccion;

	private String estado;

	private Date fechaCreacion;

	private String telefono;

	private String correo;



}