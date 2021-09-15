package proyecto2.mtsolutions.services.base;

import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.VeVentaDetalleDTO;
public interface VeVentaDetalleService {
    ListasDTO listadoVeVentaDetalle(String usuario, int cantidad, int origen) throws Exception;
    String crearVeVentaDetalle(VeVentaDetalleDTO dto, String usuario) throws Exception;
}