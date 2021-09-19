package proyecto2.mtsolutions.controller.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proyecto2.mtsolutions.dto.MtSolutionsResponse;
import proyecto2.mtsolutions.dto.base.StkRemisionesArticulosDTO;
import proyecto2.mtsolutions.services.base.StkRemisionesArticulosService;
import proyecto2.mtsolutions.services.impl.JWTServiceImpl;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping(value = "/stk_remisiones_articulos")
public class StkRemisionesArticulosController {
    @Autowired
    private StkRemisionesArticulosService service;

    @GetMapping(value = "/lista", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listaStkRemisionesArticulos(HttpServletRequest request,
                                           @RequestParam(value = "cantidad", defaultValue = "10") int cantidad,
                                           @RequestParam(value = "origen", defaultValue = "0") int origen) throws Exception {
        String user = request.getHeader(JWTServiceImpl.USER_STRING);
        log.info(request.getContextPath());
        MtSolutionsResponse resp = new MtSolutionsResponse();
        resp.setSuccess(true);
        resp.setData(this.service.listadoStkRemisionesArticulos(user, cantidad, origen));
        resp.setMessage("OK");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }    @PostMapping(value="/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearStkRemisionesArticulos(HttpServletRequest request,
                                          @RequestBody StkRemisionesArticulosDTO dto) throws Exception {
        String user = request.getHeader(JWTServiceImpl.USER_STRING);
        log.info(request.getContextPath());
        MtSolutionsResponse resp = new MtSolutionsResponse();
        resp.setSuccess(true);
        resp.setData(this.service.crearStkRemisionesArticulos(dto, user));
        resp.setMessage("OK");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}