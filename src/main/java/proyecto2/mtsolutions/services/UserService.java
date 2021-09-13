package proyecto2.mtsolutions.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import proyecto2.mtsolutions.dao.UsuarioDAO;
import proyecto2.mtsolutions.dto.FuncionalidadDTO;
import proyecto2.mtsolutions.dto.PerfilDTO;
import proyecto2.mtsolutions.dto.RolDTO;
import proyecto2.mtsolutions.dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    public final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UsuarioDAO userMapper;


    //Por mas que el metodo se llama asi, nosotros lo identificamos por email
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioDTO user = userMapper.userByName(username);
        if(user == null){
            throw new UsernameNotFoundException("El usuario " + username + " no existe");
        } else {
            user.setRoles(userMapper.rolesByUserId(user.getId()));

        }
        return buildUser(user, buildAuthorities(username, user.getRoles()));
    }


    //Esto necesitamos hacerlo porque spring necesita este Objeto de Spring para su manejo
    private UsuarioDTO buildUser(UsuarioDTO userDTO, List<GrantedAuthority> authorities){
        boolean enable = userDTO.getEstado().equalsIgnoreCase("ACTIVO");
        UsuarioDTO nuevo = new UsuarioDTO(userDTO.getId(), userDTO.getPassword(), enable,
                true, true, true, authorities);
        nuevo.setNombre(userDTO.getNombre());
        nuevo.setApellido(userDTO.getApellido());
        nuevo.setDocumento(userDTO.getDocumento());
        nuevo.setTelefono(userDTO.getTelefono());
        nuevo.setEstado(userDTO.getEstado());
        nuevo.setId(userDTO.getId());
        nuevo.setEmail(userDTO.getEmail());
        return nuevo;
    }

    //Mapeamos para que spring pueda manejar los roles
    private List<GrantedAuthority> buildAuthorities(String username, List<RolDTO> userRoles) throws UsernameNotFoundException {
        if (userRoles == null || userRoles.size() == 0)
            throw new UsernameNotFoundException("El usuario " + username + " no tiene roles asignados");

        List<GrantedAuthority> auths = new ArrayList<>();
        for (RolDTO userRole : userRoles){
            logger.info("ROL ASIGNADO A : " + username + " " + userRole);
            for (PerfilDTO perfil : userRole.getPerfiles()) {
                for (FuncionalidadDTO fun : perfil.getFuncionalidades()) {
                    auths.add(new SimpleGrantedAuthority(fun.getId()));
                }
                /*if (perfil.getFuncionalidades() != null) {
                    JsonObject jsonObject = JsonParser.parseString(perfil.getFuncionalidades()).getAsJsonObject();
                    Set<String> da = JsonParser.parseString(perfil.getFuncionalidades()).getAsJsonObject().keySet();
                    for (String key : da) {
                        String dato = jsonObject.get(key).getAsString();
                        auths.add(new SimpleGrantedAuthority(dato));
                    }
                }*/
            }
        }

        return auths;
    }

}

