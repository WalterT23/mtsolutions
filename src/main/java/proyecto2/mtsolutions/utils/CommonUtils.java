package proyecto2.mtsolutions.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import proyecto2.mtsolutions.dao.ParametroDAO;
import proyecto2.mtsolutions.dto.ParametroDTO;
import proyecto2.mtsolutions.dto.ParseDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CommonUtils {

    private final String ALFA = "ABCDEFGHIJKLMNOPQRSTVWXYZ";
    public Map<String, Object> mapa = new HashMap<>();
    @Autowired
    private ParametroDAO parametroDAO;


    public boolean isNull(Object data) {
        if (data == null) {
            return true;
        } else if (data instanceof String) {
            return ((String) data).trim().isEmpty();
        } else if (data instanceof List) {
            return ((List) data).isEmpty();
        } else if (data instanceof Map) {
            return ((Map) data).isEmpty();
        } else if (data instanceof HashMap) {
            return ((HashMap) data).isEmpty();
        }
        return false;
    }

    public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(multipart.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipart.getBytes());
        fos.close();
        return convFile;
    }

    public String cortarTexto(String txt) {
        if (!isNull(txt)) {
            txt = txt.trim();
            if (txt.length() > 15) {
                txt = txt.substring(0, txt.length()-1);
            }
            return txt;
        } else {
            return txt;
        }
    }

    public String inicialMayus(String texto) {
        String[] d = texto.split(" ");
        if(d.length != 0 && d.length == 1) {
            return texto.substring(0,1).toUpperCase() + texto.substring(1).toLowerCase();
        } else {
            String dat= "";
            for (String c : d) {
                dat = dat.concat(c.substring(0,1).toUpperCase() + c.substring(1).toLowerCase())
                        .concat(" ");
            }
            return dat;
        }
    }

    public String obtenerUsuario(String nombre, String apellido) {
        if (isNull(nombre) || isNull(apellido)) {
            return null;
        }
        String[] array = apellido.split(" ");
        if (isNull(array)) {
            return null;
        }
        return nombre.substring(0,1).toLowerCase().concat(array[0].toLowerCase());
    }

    public boolean validarCedula(String ced) {
        if (isNull(ced)) { return false; }
        Pattern patron = Pattern.compile("[0-9]*[A-Ca-c]");
        Matcher cumple = patron.matcher(ced);
        if (cumple.matches()) { return true; }
        Pattern patron2 = Pattern.compile("[0-9]*");
        Matcher cumple2 = patron2.matcher(ced);
        if (cumple2.matches()) { return true; }
        return false;
    }

    public String formatoCedula(String ced) {
        int pivot = 2;
        if (isNull(ced)) { return null; }
        int largo = ced.length();
        if (largo > pivot) {
            String aux = ced.substring(largo-pivot, largo);
            if (!isNull(aux) && aux.equals(".0")) {
                ced = ced.substring(0, largo-pivot);
                return ced;
            }
        }
        return null;
    }

    public BigDecimal roundBigDecimal(final BigDecimal input){
        return input.round(
                new MathContext(
                        input.toBigInteger().toString().length(),
                        RoundingMode.HALF_UP
                )
        );
    }

    public String longTel(String txt) {
        if (txt == null) return null;
        if (txt.length() == 9 && txt.charAt(0) == '9') return "0"+txt;
        return txt;
    }

    public ParametroDTO obtenerPropiedades(String clave) {
        ParametroDTO lev = null;
        if (!this.isNull(mapa)) {
            lev = (ParametroDTO) mapa.get(clave);
            if (this.isNull(lev)) {
                mapa.put(clave, parametroDAO.getParametroByKey(clave));
                lev = (ParametroDTO) mapa.get(clave);
            }
        } else {
            mapa.put(clave, parametroDAO.getParametroByKey(clave));
            lev = (ParametroDTO) mapa.get(clave);
        }
        return lev;
    }

    public ParseDTO parserDate(String fechaStr, String delimitador, String orden) throws ParseException {
        ParseDTO map = new ParseDTO();
        String fecha = null;
        if (this.isNull(fechaStr)) {
            map.setSuccess(false);
            map.setMessage("Fecha no encontrada");
            map.setDato(null);
            return map;
        }
        if (this.isNull(delimitador) || !(delimitador.equals("/") || delimitador.equals("-"))) {
            map.setSuccess(false);
            map.setMessage("Delimitador no encontrado o invalido (permitido: ( / ) o ( - )");
            map.setDato(null);
            return map;
        }
        map.setSuccess(true);
        map.setMessage("OK");
        String[] fechaArray = fechaStr.split(delimitador);
        if (orden == null || orden.equalsIgnoreCase("D")) {
            fecha = fechaArray[2]+"-"+fechaArray[1]+"-"+fechaArray[0] + " 00:00:00";
            //fecha = fechaStr.trim() + " 00:00:00";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = sdf.parse(fecha);
            map.setDato(d);
        } else if (orden.equalsIgnoreCase("H")) {
            fecha = fechaArray[2]+"-"+fechaArray[1]+"-"+fechaArray[0] + " 23:59:59";
            //fecha = fechaStr.trim() + " 23:59:59";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = sdf.parse(fecha);
            map.setDato(d);
        } else {
            map.setSuccess(false);
            map.setMessage("Texto en campo orden invalido");
            map.setDato(null);
            return map;
        }
        return map;
    }
}
