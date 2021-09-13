package proyecto2.mtsolutions.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import proyecto2.mtsolutions.dto.TokenDTO;

import java.io.IOException;
import java.util.Collection;

public interface JWTService {
    public String createToken(Authentication authentication) throws IOException;

    public boolean validateToken(String token);

    public Claims getClaims(String token);

    public String getUsername(String token);

    public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException;

    public String resolve(String token);

    public TokenDTO getTokenByUUID(String uuid);

    public TokenDTO getTokenByUsername(String username);

    public int deleteTokenByUUID(String uuid);
}
