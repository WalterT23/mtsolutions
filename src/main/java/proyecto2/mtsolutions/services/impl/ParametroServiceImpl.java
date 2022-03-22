package proyecto2.mtsolutions.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto2.mtsolutions.dao.ParametroDAO;
import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.exceptions.ErrorCode;
import proyecto2.mtsolutions.exceptions.ServiceException;
import proyecto2.mtsolutions.services.ParametroService;
import proyecto2.mtsolutions.utils.CommonUtils;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
@Service
public class ParametroServiceImpl implements ParametroService {

    @Autowired
    private CommonUtils ctrl;

    @Autowired
    private ParametroDAO parametroDAO;

    Map<String, Consumer<Map>> acciones;

    Consumer<Map> defaultBehavior= dto -> {
        throw new IllegalArgumentException(dto.toString());
    };

    @PostConstruct
    private void cargaValorMap() {
        acciones = new HashMap<>();
        acciones.put(EMPRESA, dto -> execEmpresa(dto));
        acciones.put(SUCURSAL, dto -> execSucursal(dto));
        acciones.put(MONEDA, dto -> execMoneda(dto));
        acciones.put(IMPUESTO, dto -> execImpuesto(dto));
        acciones.put(CATEGORIA, dto -> execCategoria(dto));
        acciones.put(FAMILIA, dto -> execFamilia(dto));
        acciones.put(TIPO_GASTO, dto -> execTipoGasto(dto));
    }

    @Override
    public ListasDTO listadoParametros() throws Exception {
        try {
            log.info(getClass().toString());
            //log.info("Usuario conectado: "+usuario);
            log.info("Servico: listadoParametros");
            ListasDTO lista = new ListasDTO();
            lista.setLista(parametroDAO.traerHelpers());
            lista.setTotalRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            log.info("Datos: "+lista.toString());
            return lista;
        }catch (Exception e) {
            log.error(e.toString());
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    @Override
    public ListasDTO listadoClaves(FiltroDTO filtro) throws Exception {
        try {
            log.info(getClass().toString());
            //log.info("Usuario conectado: "+usuario);
            log.info("Servico: listadoClaves");
            ListasDTO lista = new ListasDTO();
            Map map = new HashMap();
            map.put("lista", lista);
            map.put("filtro", filtro);
            if (CommonUtils.notNull(filtro)) {
                acciones.getOrDefault(filtro.getValor(), defaultBehavior).accept(map);
                //lista.setLista(parametroDAO.traerHelpers());
                lista.setTotalRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
                lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
                log.info("Datos: "+lista.toString());
            }


            return lista;
        }catch (Exception e) {
            log.error(e.toString());
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    public void execEmpresa(Map map) {
        ListasDTO lista = (ListasDTO) map.get("lista");
        FiltroDTO filtro = (FiltroDTO) map.get("filtro");
        lista.setLista(parametroDAO.traerEmpresas(filtro));
        lista.setTotalRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
        lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
    }

    public void execSucursal(Map map) {
        ListasDTO lista = (ListasDTO) map.get("lista");
        FiltroDTO filtro = (FiltroDTO) map.get("filtro");
        lista.setLista(parametroDAO.traerSucursal(filtro));
        lista.setTotalRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
        lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
    }

    public void execMoneda(Map map) {
        ListasDTO lista = (ListasDTO) map.get("lista");
        FiltroDTO filtro = (FiltroDTO) map.get("filtro");
        lista.setLista(parametroDAO.traerMonedas(filtro));
        lista.setTotalRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
        lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
    }

    public void execImpuesto(Map map) {
        ListasDTO lista = (ListasDTO) map.get("lista");
        FiltroDTO filtro = (FiltroDTO) map.get("filtro");
        lista.setLista(parametroDAO.traerImpuestos(filtro));
        lista.setTotalRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
        lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
    }

    public void execCategoria(Map map) {
        ListasDTO lista = (ListasDTO) map.get("lista");
        FiltroDTO filtro = (FiltroDTO) map.get("filtro");
        lista.setLista(parametroDAO.traerCategorias(filtro));
        lista.setTotalRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
        lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
    }

    public void execFamilia(Map map) {
        ListasDTO lista = (ListasDTO) map.get("lista");
        FiltroDTO filtro = (FiltroDTO) map.get("filtro");
        lista.setLista(parametroDAO.traerFamilias(filtro));
        lista.setTotalRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
        lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
    }

    public void execTipoGasto(Map map) {
        ListasDTO lista = (ListasDTO) map.get("lista");
        FiltroDTO filtro = (FiltroDTO) map.get("filtro");
        lista.setLista(parametroDAO.traerTipoGastos(filtro));
        lista.setTotalRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
        lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
    }
}
