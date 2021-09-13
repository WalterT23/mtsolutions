package proyecto2.mtsolutions.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Controller
@RequestMapping(value = "/report")
public class ReportController {
   /* @Autowired
    private ReportService service;

    @PostMapping(value = "/tipo/{cls}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<WallyResponse> endosadoCard(@PathVariable String cls, MultipartFile archivo,
                                                      String pathDestino, String afinidad, MultipartFile csvTitulos) {
        WallyResponse resp = new WallyResponse();
        try {
            resp.setData(this.service.reportCardPrint(cls, archivo, pathDestino, afinidad, csvTitulos));
            resp.setSuccess(true);
            resp.setMessage("Archivo creado tipo -" + cls);
        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setMessage("Error -> " + e.getMessage());
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/cedulasVencidas/{tipoReporte}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WallyResponse> reporteCedulasvencidas(@PathVariable String tipoReporte) {
        WallyResponse resp = new WallyResponse();
        try {
            resp.setSuccess(true);
            resp.setData(this.service.reportExpiredID(tipoReporte));
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setMessage(e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.CONFLICT);
        }

    }

    @GetMapping(value = "/seguimientoCliente/{tipoReporte}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WallyResponse> reporteSeguimientoaClientes(@PathVariable String tipoReporte) {
        WallyResponse resp = new WallyResponse();
        try {
            resp.setSuccess(true);
            resp.setData(this.service.reportTracingClient(tipoReporte));
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setMessage(e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.CONFLICT);
        }

    }

    @GetMapping(value = "/historialCuentas/{tipoReporte}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WallyResponse> reporteHistorialCuentas(@PathVariable String tipoReporte) {
        WallyResponse resp = new WallyResponse();
        try {
            resp.setSuccess(true);
            resp.setData(this.service.reportHistoryAccount(tipoReporte));
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setMessage(e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.CONFLICT);
        }

    }

    @GetMapping(value = "/pruebaPdf", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WallyResponse> pruebaReporte() {
        WallyResponse resp = new WallyResponse();
        try {
            resp.setSuccess(true);
            resp.setData(this.service.reportePdfTrial());
        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setMessage("Error -> " + e.getMessage());
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/pruebaMybatisPoirot", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WallyResponse> pruebaMybatis() {
        WallyResponse resp = new WallyResponse();
        try {
            resp.setSuccess(true);
            resp.setData(this.service.getDTO());
        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setMessage("Error -> " + e.getMessage());
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/pruebaMybatisPatovik", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WallyResponse> pruebaMybatis2() {
        WallyResponse resp = new WallyResponse();
        try {
            resp.setSuccess(true);
            resp.setData(this.service.getDTO2());
        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setMessage("Error -> " + e.getMessage());
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping(value = "/tipo/{cls}/v2", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> endosadoCardV2(@PathVariable String cls, MultipartFile archivo, String afinidad, MultipartFile csvTitulos, HttpServletRequest request) {

        try {
            // tomamos el directorio de archivos temporales del servidor
            String pathDestino = System.getProperty("java.io.tmpdir");

            Assert.notNull(pathDestino, "No se pudo obtener el directorio de archivos temporales");

            Assert.isTrue(Files.exists(Paths.get(pathDestino)), "No existe el directorio de archivos temporales: " + pathDestino);

            FileDTO oDto = this.service.reportCardPrint(cls, archivo, pathDestino, afinidad, csvTitulos);

            Path oPath = Paths.get(oDto.getArchivo().toString());
            Resource oRecurso = new UrlResource(oPath.toUri());

            // Try to determine file's content type
            String cTipo = MediaType.APPLICATION_OCTET_STREAM_VALUE;
            try {
                cTipo = request.getServletContext().getMimeType(oRecurso.getFile().getAbsolutePath());
            } catch (IOException ex) {
                log.info("Could not determine file type.");
            }

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(cTipo))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + oRecurso.getFilename() + "\"")
                    .body(oRecurso);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(value = "/comisiones", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> obtenerComision(@RequestBody FiltroDTO dto, HttpServletRequest request,
                                             @RequestParam(value = "cantidad", defaultValue = "10") int cantidad,
                                             @RequestParam(value = "origen", defaultValue = "0") int origen) throws Exception {
        String user = request.getHeader(JWTServiceImpl.USER_STRING);
        WallyResponse resp = new WallyResponse();
        resp.setSuccess(true);
        resp.setData(this.service.getComision(dto, user, cantidad, origen));
        resp.setMessage("OK");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }*/

}

