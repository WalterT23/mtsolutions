package proyecto2.mtsolutions.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Slf4j
//@Service
public class ReportServiceImpl /*implements ReportService */{
/*    @Autowired
    private ServletContext ctx;
    @Autowired
    private CommonUtils ctrl;
    @Autowired
    private ReportGenerator rp;
    @Autowired
    private PoirotDAO daoPoirot;
    @Autowired
    //@Qualifier("patovikMapper")
    private PatovikDAO daoPatovik;
    @Autowired
    private JcardDAO daoJcard;

    @Autowired
    private EmpresaDAO empresaDAO;

    @Value("${parametro.we.report.comision.status}")
    private String stado;

    @Override
    public FileDTO reportCardPrint(String tipo, MultipartFile archivo, String path, String idAfinidad, MultipartFile csvTitulos) throws Exception {
        List<TxtDTO> captura = null;
        TituloDTO titulos = null;
        FileDTO ret = new FileDTO();
        try {
            if (!ctrl.isNull(tipo) && !ctrl.isNull(archivo) && !ctrl.isNull(path)) {
                captura = lecturaArchivo(ctrl.multipartToFile(archivo));
                titulos = lecturaArchivoTitulos(ctrl.multipartToFile(csvTitulos));
                if (tipo.equalsIgnoreCase(TARJETA)) {
                    if (!ctrl.isNull(captura)) {
                        ret.setArchivo(crearCsv(captura, path, TARJETA, idAfinidad, titulos));
                    } else {
                        throw new Exception("El archivo está vacio");
                    }
                } else if (tipo.equalsIgnoreCase(CUENTA)) {
                    ret.setArchivo(crearCsv(captura, path, CUENTA, idAfinidad, titulos));
                } else {
                    throw new Exception("Parametro no permitido "+tipo);
                }
            } else if (ctrl.isNull(tipo)) {
                throw new Exception("Falta definir el tipo de cuenta en URL");
            } else if (ctrl.isNull(archivo)) {
                throw new Exception("No se encontró el archivo adjunto");
            } else if (ctrl.isNull(path)) {
                throw new Exception("Falta definir la ruta para descarga de archivo");
            }
            return ret;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public FileDTO reportExpiredID(String tipoReporte) throws Exception {
        FileDTO ret = new FileDTO();
        if (!ctrl.isNull(tipoReporte)) {
            List<PoirotDTO> datos = null;
            if(tipoReporte.equalsIgnoreCase(E) || tipoReporte.equalsIgnoreCase(P)) {
                datos = (List<PoirotDTO>) this.obtenerDatos(EXP);
                // ret.setArchivo(rp.getByteReports(tipoReporte, false, datos, null, ctx, "reportes/cedulasVencidas.jrxml"));
                ret.setTipo(tipoReporte.equalsIgnoreCase(E)?XLS:PDF);
                ret.setNombre("Reporte cedulas vencidas");
            } else {
                throw new Exception("Tipo de reporte seleccionado no valido");
            }
        } else {
            throw new Exception("Debe seleccionar un tipo de reporte");
        }
        return ret;
    }

    @Override
    public FileDTO reportTracingClient(String tipoReporte) throws Exception {
        FileDTO ret = new FileDTO();
        if (!ctrl.isNull(tipoReporte)) {
            List<SeguimientoDTO> datos = null;
            if (tipoReporte.equalsIgnoreCase(E) || tipoReporte.equalsIgnoreCase(P)) {
                datos = (List<SeguimientoDTO>) this.obtenerDatos(SCL);
                //   ret.setArchivo(rp.getByteReports(tipoReporte, false, datos, null, ctx, "reportes/seguimientosClientes.jrxml"));
                ret.setTipo(tipoReporte.equalsIgnoreCase(E)?XLS:PDF);
                ret.setNombre("Reporte_seguimiento_cliente");
            } else {
                throw new Exception("Tipo de reporte seleccionado no valido");
            }
        } else {
            throw new Exception("Debe seleccionar un tipo de reporte");
        }
        return ret;
    }

    @Override
    public FileDTO reportHistoryAccount(String tipoReporte) throws Exception {
        FileDTO ret = new FileDTO();
        if (!ctrl.isNull(tipoReporte)) {
            List<JcardDTO> datos = null;
            if (tipoReporte.equalsIgnoreCase(E) || tipoReporte.equalsIgnoreCase(P)) {
                datos = (List<JcardDTO>) this.obtenerDatos(HCU);
                //  ret.setArchivo(rp.getByteReports(tipoReporte, false, datos, null, ctx, "reportes/historialCuentas.jrxml"));
                ret.setTipo(tipoReporte.equalsIgnoreCase(E) ? XLS : PDF);
                ret.setNombre("Reporte_historial_cuentas");
            } else {
                throw new Exception("Tipo de reporte seleccionado no valido");
            }
        } else {
            throw new Exception("Debe seleccionar un tipo de reporte");
        }
        return ret;
    }

    @Override
    public FileDTO reportePdfTrial() throws Exception {
        FileDTO ar = new FileDTO();
        try {
            //  ar.setArchivo(rp.getByteReports("E", false, null, null, ctx, "reportes/cedulasVencidas.jrxml"));
        } catch (Exception e) {
            throw e;
        }
        return ar;
    }

    @Override
    public List<PoirotDTO> getDTO() throws Exception {
        List<PoirotDTO> r = null;
        try {
            //r = daoPoirot.getExpiredWallet();
            r = (List<PoirotDTO>) this.obtenerDatos(EXP);
        } catch (Exception e) {
            throw e;
        }
        return r;
    }

    @Override
    public List<PatovikDTO> getDTO2() throws Exception {
        List<PatovikDTO> r = null;

        try {
            r = daoPatovik.getUsers();
        } catch (Exception e) {
            throw e;
        }
        return r;
    }

    private List<?> obtenerDatos( String t) {
        if (!ctrl.isNull(t) && t.equalsIgnoreCase(EXP)) {
            List<PoirotDTO> dato = daoPoirot.getExpiredWallet();
            if (!ctrl.isNull(dato)) {
                dato.forEach(x -> {
                    x.setNroBilletera(daoPatovik.obtenerBilletera(x.getIdCliente()));
                });
            }
            return dato;
        } else if (!ctrl.isNull(t) && t.equalsIgnoreCase(SCL)) {
            List<SeguimientoDTO> dato = daoPoirot.getSeguimientos();
            return dato;
        } else if (!ctrl.isNull(t) && t.equalsIgnoreCase(HCU)) {
            List<JcardDTO> dato = daoJcard.getHistorialCuentas();
            return dato;

        }
        return null;
    }

    private TituloDTO lecturaArchivoTitulos(File data) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String txt = null;
        TituloDTO titulos = new TituloDTO();
        try {
            archivo = data;
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null) {
                txt = new String();
                String[] sep = linea.split(";");
                for (int i = 1; i < sep.length; i++) {
                    if (sep[0].equalsIgnoreCase(TARJETA)) {
                        txt = sep[i];
                        titulos.getTituloTarjeta().add(txt);
                    } else if (sep[0].equalsIgnoreCase(CUENTA)) {
                        txt = sep[i];
                        titulos.getTituloCuenta().add(txt);
                    }
                }

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return titulos;
    }

    private List<TxtDTO> lecturaArchivo(File data) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        TxtDTO txt = null;
        List<TxtDTO> listTxt = new ArrayList<>();
        try {
            archivo = data;
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null) {
                txt = new TxtDTO();
                String[] sep = linea.split(";");
                txt.setTipoPlastico(sep[4]);
                txt.setNroTarjeta(sep[8]);
                txt.setTipoTarjeta(sep[9]);
                txt.setVencimiento(sep[20]);
                listTxt.add(txt);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return listTxt;
    }

    public String crearCsv(List<TxtDTO> data, String path, String tipo, String idAfinidad, TituloDTO lisTitulo) throws Exception {
        try {
            File newFolder = new File(path);
            boolean dirCreated = newFolder.mkdir();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-dd_HH-mm-ss");
            LocalDateTime now = LocalDateTime.now();


            // agregado -- CDGS - 06/04/2021
            // verificamos que tenga el separador de directorios al final, y si no lo tiene, lo agregamos
            if(!path.endsWith(File.separator))
                path = path.concat(File.separator);

            if (tipo.equalsIgnoreCase(TARJETA)) {
                TarjetaDTO csvText = null;
                String fileName = "TARJETA_" + dtf.format(now) + "."+TXT;

                File statText = new File(path + fileName);
                FileOutputStream is = new FileOutputStream(statText);
                OutputStreamWriter osw = new OutputStreamWriter(is);
                Writer w = new BufferedWriter(osw);
                for (int i = 0; i < data.size(); i++) {
                    /*HABILITAR LA LINEA DE ABAJO PARA PONER EL TITULO
                    //if (i == 0) w.write(lisTitulo.toStringTarjeta() + "\n");
                    csvText = new TarjetaDTO();
                    csvText.setNroTarjeta(data.get(i).getNroTarjeta().trim());
                    csvText.setTipoPlastico(data.get(i).getTipoPlastico().trim());
                    csvText.setNroControl(i + 1);
                    csvText.setIdAfinidad(idAfinidad);
                    w.write(csvText.toString() + "\n");
                }
                w.close();

                log.info("Archivo creado: {}", statText.getAbsolutePath());

                return statText.getAbsolutePath();

            } else if (tipo.equalsIgnoreCase(CUENTA)) {
                CuentasDTO csvText = null;
                String fileName = "CUENTA_" + dtf.format(now) + "."+TXT;
                File statText = new File(path + fileName);
                FileOutputStream is = new FileOutputStream(statText);
                OutputStreamWriter osw = new OutputStreamWriter(is);
                Writer w = new BufferedWriter(osw);
                for (int i = 0; i < data.size(); i++) {
                    /*HABILITAR LA LINEA DE ABAJO PARA PONER EL TITULO
                    //if (i==0) w.write(lisTitulo.toStringCuenta() + "\n");
                    csvText = new CuentasDTO();
                    csvText.setNroControl(i + 1);
                    csvText.setCodigoAfinidad(idAfinidad);
                    w.write(csvText.toString() + "\n");
                }
                w.close();

                log.info("Archivo creado: {}", statText.getAbsolutePath());

                return statText.getAbsolutePath();

            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            throw new Exception("Error al escribir el archivo");
        }
        return "Error";
    }

    @Override
    public Map<String, Object> getComision(FiltroDTO dto, String user, int cantidad, int origen) throws Exception {
        Map<String, Object> map = new HashMap<>();
        ListasDTO ll = new ListasDTO();
        if (ctrl.isNull(dto) || ctrl.isNull(dto.getValor())) {log.error("Parametro vacio: "+dto.toString());throw new ServiceException(ErrorCode.NO_ENTERPRISE_ID);}
        if (ctrl.isNull(dto.getFechaDesde()) || ctrl.isNull(dto.getFechaHasta())) {log.error("Rango de fecha no valido: "+dto.toString());throw new ServiceException(ErrorCode.NO_DATE_CONTENT);}
        ParseDTO parseD = ctrl.parserDate(dto.getFechaDesde(),"/","D");
        if (parseD.isSuccess()) {
            dto.setFechaDesdeDate(parseD.getDato());
        } else {
            log.error(parseD.getMessage());
            throw new ServiceException(ErrorCode.FECHA_PARSE, parseD.getMessage());
        }
        ParseDTO parseH = ctrl.parserDate(dto.getFechaHasta(),"/","H");
        if (parseH.isSuccess()) {
            dto.setFechaHastaDate(parseH.getDato());
        } else {
            log.error(parseD.getMessage());
            throw new ServiceException(ErrorCode.FECHA_PARSE, parseD.getMessage());
        }

        ParametroDTO valStados = ctrl.obtenerPropiedades(this.stado);
        if (ctrl.isNull(valStados) || ctrl.isNull(valStados.getValue())) {
            log.error("No se encontro el valor de la clave "+this.stado);
            throw new ServiceException(ErrorCode.NO_CONFIG_LOAD);
        }
        //List<String> ls = Arrays.asList(valStados.getValue().split(","));
        List<String> ls = new ArrayList<>();
        String[] xx = valStados.getValue().split(",");
        for (String x: xx) {
            ls.add(x);
        }
        dto.setLista(ls);
        ll.setLista(empresaDAO.getCabeceraOpComision(dto,cantidad, origen));
        ll.setTotalRegistros(empresaDAO.getCabeceraOpComisionCount(dto));
        ll.setCantActualRegistros(new BigDecimal(ll.getLista().size()));
        map.put("lista",ll);
        TotalesDTO wetotales = empresaDAO.getTotalesOp(dto);
        map.put("totalwe", wetotales);
        FiltroDTO filtroJcard = new FiltroDTO();
        filtroJcard.setFechaDesdeDate(dto.getFechaDesdeDate());
        filtroJcard.setFechaHastaDate(dto.getFechaHastaDate());
        EmpresaDTO emp = daoPatovik.getData(dto.getValor());
        filtroJcard.setValor(emp.getBilleteraMadre());
        filtroJcard.setLista(empresaDAO.getCodeServiceJcard(dto));
        if (ctrl.isNull(filtroJcard.getLista())) { filtroJcard.setLista(null);}
        TotalesDTO jcardtotales = daoJcard.sumaTotalOperaciones(filtroJcard);
        map.put("totaljcard", jcardtotales);
        return map;
    }
*/
}

