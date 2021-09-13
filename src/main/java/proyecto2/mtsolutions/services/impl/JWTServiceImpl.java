package proyecto2.mtsolutions.services.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import proyecto2.mtsolutions.configuration.security.SimpleGrantedAuthorityMixin;
import proyecto2.mtsolutions.dao.TokenDAO;
import proyecto2.mtsolutions.dto.TokenDTO;
import proyecto2.mtsolutions.dto.UsuarioDTO;
import proyecto2.mtsolutions.services.JWTService;
import proyecto2.mtsolutions.utils.TXUUIDGenerator;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Slf4j
@Service
public class JWTServiceImpl implements JWTService {
    public final Logger logger = LoggerFactory.getLogger(getClass());
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String USER_STRING = "user-token";

    private String accessTokenSecret;
    private long jwtExpirationDateInMs;
    private long refreshExpirationDateInMs;

    @Value("${jwt.accessTokenSecret}")
    public void setAccessTokenSecret(String accessTokenSecret) {
        this.accessTokenSecret = Base64Utils.encodeToString(accessTokenSecret.getBytes());
    }

    @Value("${jwt.accessTokenExpirationInMilliseconds}")
    public void setJwtExpirationInMs(int jwtExpirationInMs) {
        this.jwtExpirationDateInMs = jwtExpirationInMs;
    }

    @Value("${jwt.refreshTokenExpirationInMilliseconds}")
    public void setRefreshExpirationDateInMs(int refreshExpirationDateInMs) {
        this.refreshExpirationDateInMs = refreshExpirationDateInMs;
    }

    @Autowired
    private TokenDAO tokenDAO;

    @Override
    public String createToken(Authentication authentication) throws IOException {

        String username = ((User) authentication.getPrincipal()).getUsername();
        String nombre = ((UsuarioDTO) authentication.getPrincipal()).getNombre();
        String apellido = ((UsuarioDTO) authentication.getPrincipal()).getApellido();
        String documento = ((UsuarioDTO) authentication.getPrincipal()).getDocumento();
        String telefono = ((UsuarioDTO) authentication.getPrincipal()).getTelefono();
        String estado = ((UsuarioDTO) authentication.getPrincipal()).getEstado();
        String email = ((UsuarioDTO) authentication.getPrincipal()).getEmail();
        String id = (((UsuarioDTO) authentication.getPrincipal()).getId());
        String uuid = new TXUUIDGenerator().generate();

        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();

        Claims accessTokenclaims = Jwts.claims();
        accessTokenclaims.put("authorities", new ObjectMapper().writeValueAsString(roles));
        accessTokenclaims.put("nombre", new ObjectMapper().writeValueAsString(nombre));
        accessTokenclaims.put("apellido", new ObjectMapper().writeValueAsString(apellido));
        accessTokenclaims.put("documento", new ObjectMapper().writeValueAsString(documento));
        accessTokenclaims.put("telefono", new ObjectMapper().writeValueAsString(telefono));
        accessTokenclaims.put("estado", new ObjectMapper().writeValueAsString(estado));
        accessTokenclaims.put("email", new ObjectMapper().writeValueAsString(email));
        accessTokenclaims.put("id", new ObjectMapper().writeValueAsString(id));
        accessTokenclaims.put("uuid", new ObjectMapper().writeValueAsString(uuid));
        String token = null;
        try {
            token = Jwts.builder().setClaims(accessTokenclaims).setSubject(username)
                    .signWith(SignatureAlgorithm.HS512, this.accessTokenSecret.getBytes()).setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + this.jwtExpirationDateInMs)).compact();
        } catch (Exception e) {
            log.error(e.toString());
            throw e;
        }


        try {
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setUuid(uuid);
            tokenDTO.setExpiration((new Date(System.currentTimeMillis() + this.refreshExpirationDateInMs)));
            tokenDTO.setIssuedAt(new Date());
            tokenDTO.setRemoteIp("");
            tokenDTO.setTokenType("");
            tokenDTO.setUserAgent("");
            tokenDTO.setUsername(username);
            tokenDTO.setSecurityProfileId("P5-Web");
            tokenDTO.setAppId("mt_solutions");
            tokenDTO.setValid(true);

            TokenDTO previousToken = tokenDAO.tokenByUsername(username);

            if (previousToken != null) {
                //tokenDAO.deleteToken(previousToken.getUuid());
                return null;
            }
            tokenDAO.insertToken(tokenDTO);

        } catch (Exception e) {
            logger.error("Exception: {}", e.getMessage());
        }

        return token;
    }

    @Override
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
            throw e;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
            throw e;
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
            throw e;
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
            throw e;
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Claims getClaims(String token) {
        return (Claims) Jwts.parser().setSigningKey(this.accessTokenSecret.getBytes()).parseClaimsJws(resolve(token))
                .getBody();
    }

    @Override
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    @Override
    public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException {

        Object roles = getClaims(token).get("authorities");

        Collection<? extends GrantedAuthority> authorities = Arrays
                .asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
                        .readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));

        return authorities;
    }

    @Override
    public String resolve(String token) {
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            return token.replace(TOKEN_PREFIX, "");
        }
        return null;
    }

    @Override
    public TokenDTO getTokenByUUID(String uuid) {
        try {
            TokenDTO tokenDTO = tokenDAO.tokenByValue(uuid);
            return tokenDTO;
        } catch (Exception e) {
            logger.error("getTokenDTOByUuidException: {}", e.getMessage());
            return null;
        }

    }

    @Override
    public TokenDTO getTokenByUsername(String username) {
        try {
            TokenDTO tokenDTO = tokenDAO.tokenByUsername(username);
            return tokenDTO;
        } catch (Exception e) {
            logger.error("getTokenDTOByUsernameException: {}", e.getMessage());
            return null;
        }

    }

    @Override
    public int deleteTokenByUUID(String uuid) {
        try {
            tokenDAO.deleteToken(uuid);
            return 1;
        } catch (Exception e) {
            logger.error("deleteTokenDTOByUuidException: {}", e.getMessage());
            return 0;
        }

    }
}
