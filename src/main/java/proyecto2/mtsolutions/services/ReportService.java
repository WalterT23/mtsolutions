package proyecto2.mtsolutions.services;

import org.springframework.web.multipart.MultipartFile;
import proyecto2.mtsolutions.dto.FileDTO;
import proyecto2.mtsolutions.dto.FiltroDTO;
import java.util.Map;

public interface ReportService {
    public final static String TARJETA = "tarjeta";
    public final static String CUENTA = "cuenta";
    public final static String EXP = "EXP";
    public final static String SCL = "SCL";
    public final static String HCU = "HCU";
    public final static String PDF = "pdf";
    public final static String XLS = "xls";
    public final static String TXT = "txt";
    public final static String E = "E";
    public final static String P = "P";
    FileDTO reportCardPrint(String tipo, MultipartFile archivo, String path, String idAfinidad, MultipartFile csvTitulos) throws Exception;
    FileDTO reportePdfTrial() throws Exception;
    FileDTO reportExpiredID(String tipoReporte) throws Exception;
    FileDTO reportTracingClient(String tipoReporte) throws Exception;
    FileDTO reportHistoryAccount(String tipoReporte) throws Exception;
    Map<String, Object> getComision(FiltroDTO dto, String user, int cantidad, int origen) throws Exception;
}
