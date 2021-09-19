package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.CoBancoDTO;
public interface CoBancoService {
    ListasDTO listadoCoBanco(String usuario, int cantidad, int origen) throws Exception;
    String crearCoBanco(CoBancoDTO dto, String usuario) throws Exception;
}