package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.StkCategoriaDTO;
public interface StkCategoriaService {
    ListasDTO listadoStkCategoria(String usuario, int cantidad, int origen) throws Exception;
    String crearStkCategoria(StkCategoriaDTO dto, String usuario) throws Exception;
}