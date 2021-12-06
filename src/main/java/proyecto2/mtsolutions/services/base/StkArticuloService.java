package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoProveedorDTO;
import proyecto2.mtsolutions.dto.base.StkArticuloDTO;
public interface StkArticuloService {
    ListasDTO listadoStkArticulo(String usuario, int cantidad, int origen) throws Exception;
    String crearStkArticulo(StkArticuloDTO dto, String usuario) throws Exception;
    ListasDTO buscarArticulo(FiltroDTO filtro, String usuario, int cantidad, int origen) throws Exception;
    CoProveedorDTO obtenerArticulo(StkArticuloDTO dto, String usuario) throws Exception;
}