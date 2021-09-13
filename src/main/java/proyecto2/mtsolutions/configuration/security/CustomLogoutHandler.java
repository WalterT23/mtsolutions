package proyecto2.mtsolutions.configuration.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import proyecto2.mtsolutions.dao.TokenDAO;
import proyecto2.mtsolutions.dto.TokenDTO;
import proyecto2.mtsolutions.services.JWTService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class CustomLogoutHandler implements LogoutHandler {

    @Autowired
    private TokenDAO tokenDAO;

    @Autowired
    private JWTService jwtService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // TODO Auto-generated method stub
        try {
            String token = request.getHeader("user");
            if (token != null) {
                TokenDTO user = jwtService.getTokenByUsername(token);
                int x = tokenDAO.deleteToken(user.getUuid());
                if (x != 0) {
                    log.info("Logout exitoso");
                } else {
                    log.error("No fue posible ejecutar el logout");
                }
            }
        } catch (Exception e) {
            log.error(e.toString());
        }

    }

}
