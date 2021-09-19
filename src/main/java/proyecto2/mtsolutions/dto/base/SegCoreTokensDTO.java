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
public class SegCoreTokensDTO {

	private Integer id;

	private String uuid;

	private Date issuedAt;

	private String remoteIp;

	private String tokenType;

	private String userAgent;

	private String securityProfileId;

	private String appId;

	private Boolean valid;

	private String username;

	private Date expiration;



}