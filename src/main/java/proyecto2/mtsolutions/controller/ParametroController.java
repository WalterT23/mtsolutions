package proyecto2.mtsolutions.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.MtSolutionsResponse;
import proyecto2.mtsolutions.services.ParametroService;
import proyecto2.mtsolutions.services.impl.JWTServiceImpl;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping(value = "/parametros")
public class ParametroController {
    @Autowired
    private ParametroService service;

    @GetMapping(value = "/helper", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listaParametros(HttpServletRequest request) throws Exception {
        String user = request.getHeader(JWTServiceImpl.USER_STRING);
        log.info(request.getContextPath());
        MtSolutionsResponse resp = new MtSolutionsResponse();
        resp.setSuccess(true);
        resp.setData(this.service.listadoParametros());
        resp.setMessage("OK");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping(value = "/clave", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listaClave(@RequestBody FiltroDTO filtro,
                                        HttpServletRequest request) throws Exception {
        String user = request.getHeader(JWTServiceImpl.USER_STRING);
        log.info(request.getContextPath());
        MtSolutionsResponse resp = new MtSolutionsResponse();
        resp.setSuccess(true);
        resp.setData(this.service.listadoClaves(filtro));
        resp.setMessage("OK");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
