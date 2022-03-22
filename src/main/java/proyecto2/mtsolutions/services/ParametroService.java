package proyecto2.mtsolutions.services;

import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.ListasDTO;

public interface ParametroService {
    final static String EMPRESA = "BA_EMPRESA";
    final static String SUCURSAL = "BA_SUCURSAL";
    final static String MONEDA = "BA_MONEDA";
    final static String IMPUESTO = "BA_IMPUESTO";
    final static String CATEGORIA = "STK_CATEGORIA";
    final static String FAMILIA = "STK_FAMILIA";
    final static String TIPO_GASTO = "CO_TIPO_GASTO";


    ListasDTO listadoParametros() throws Exception;
    ListasDTO listadoClaves(FiltroDTO filtro) throws Exception;
}
