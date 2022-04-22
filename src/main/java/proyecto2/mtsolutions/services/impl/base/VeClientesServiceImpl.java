package proyecto2.mtsolutions.services.impl.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto2.mtsolutions.dao.base.VeClientesDAO;
import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.ListasDTO;
import proyecto2.mtsolutions.dto.base.VeClientesDTO;
import proyecto2.mtsolutions.exceptions.ErrorCode;
import proyecto2.mtsolutions.exceptions.ServiceException;
import proyecto2.mtsolutions.services.base.VeClientesService;
import proyecto2.mtsolutions.utils.CommonUtils;

import java.math.BigDecimal;

@Slf4j
@Service
public class VeClientesServiceImpl implements VeClientesService {

    @Autowired
    private CommonUtils ctrl;

    @Autowired
    private VeClientesDAO veClientesDAO;

    @Override
    public ListasDTO listado(String usuario, int cantidad, int origen) throws ServiceException {
        try {
            ListasDTO lista = new ListasDTO();
            lista.setLista(veClientesDAO.getLista(cantidad, origen));
            lista.setTotalRegistros(veClientesDAO.getListCount());
            lista.setCantActualRegistros(ctrl.isNull(lista.getLista())? BigDecimal.valueOf(0):BigDecimal.valueOf(lista.getLista().size()));
            return lista;
        }catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    @Override
    public String crear(VeClientesDTO dto, String usuario) throws ServiceException {
        try {
            String re = "Registro creado correctamente";
            checkDataInformation(dto);
            verificarExistencia(dto);

            int xc = veClientesDAO.insert(dto);
            if (xc==0) {
                throw new ServiceException(ErrorCode.DATABASE_ERROR);
            }
            return re;
        } catch (ServiceException e) {
          throw e;
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.toString());
        }
    }

    @Override
    public String update(VeClientesDTO dto, String usuario) throws ServiceException {
        checkDataIdentifier(dto);
        veClientesDAO.update(dto);
        return null;
    }

    @Override
    public VeClientesDTO get(VeClientesDTO dto, String usuario) throws ServiceException {
        checkDataIdentifier(dto);
        return veClientesDAO.getById(dto.getIdCliente());
    }

    @Override
    public ListasDTO buscar(FiltroDTO filtro, String usuario, int cantidad, int origen) throws ServiceException {
        return null;
    }

    public void checkData(VeClientesDTO dto) throws ServiceException{
        if(ctrl.isNull(dto))
            throw new ServiceException(ErrorCode.NO_BODY_DATA);
    }

    //Desde el front se está enviando solo el identificador actualmente, pero dentro de un objeto
    public void checkDataIdentifier(VeClientesDTO dto) throws ServiceException{
        checkData(dto);
        if (ctrl.isNull(dto.getIdCliente())) {
            throw new ServiceException(ErrorCode.PARAMETROS_FALTANTES);
        }
    }

    public void checkDataInformation(VeClientesDTO dto) throws ServiceException {
        checkData(dto);

        boolean faltaParametro = false;

        if(dto.getRuc() == null)
            faltaParametro = true;

        if(dto.getDireccion() == null)
            faltaParametro = true;

        if(dto.getRazonSocial() == null)
            faltaParametro = true;

        if(dto.getTelefono() == null)
            faltaParametro = true;


        if(faltaParametro){
            throw new ServiceException(ErrorCode.PARAMETROS_FALTANTES);
        }
    }

    public void verificarExistencia(VeClientesDTO dto){
        if(veClientesDAO.yaExiste(dto)){
            throw new ServiceException(ErrorCode.CUSTOM_ERROR, "Ya existe cliente");
        }
    }
}