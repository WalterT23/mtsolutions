package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoProveedorDTO;
public interface CoProveedorService {
    ListasDTO listadoCoProveedor(String usuario, int cantidad, int origen) throws Exception;
    String crearCoProveedor(CoProveedorDTO dto, String usuario) throws Exception;
    ListasDTO buscarProveedor(FiltroDTO filtro, String usuario, int cantidad, int origen) throws Exception;
    CoProveedorDTO obtenerProveedor(CoProveedorDTO dto, String usuario) throws Exception;
}