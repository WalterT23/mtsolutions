package proyecto2.mtsolutions.services;

import proyecto2.mtsolutions.dto.RolDTO;
import proyecto2.mtsolutions.dto.RolUpdateDTO;

public interface RolService {
    String actualizarRol(RolUpdateDTO dto, String user);
    String crearRol(RolDTO dto, String user);
}
