package proyecto2.mtsolutions.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proyecto2.mtsolutions.dto.FiltroDTO;
import proyecto2.mtsolutions.dto.MtSolutionsResponse;
import proyecto2.mtsolutions.dto.UsuarioDTO;
import proyecto2.mtsolutions.services.UsuarioService;
import proyecto2.mtsolutions.services.impl.JWTServiceImpl;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping(value = "/lista", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listaUsuarios(HttpServletRequest request,
                                           @RequestParam(value = "cantidad", defaultValue = "10") int cantidad,
                                           @RequestParam(value = "origen", defaultValue = "0") int origen) throws Exception {
        String user = request.getHeader(JWTServiceImpl.USER_STRING);
        log.info(request.getContextPath());
        MtSolutionsResponse resp = new MtSolutionsResponse();
        resp.setSuccess(true);
        resp.setData(this.service.listadoUsuarios(user, cantidad, origen));
        resp.setMessage("OK");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping(value = "/buscador", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscadorUsuario(HttpServletRequest request,
                                             @RequestBody FiltroDTO filtro,
                                             @RequestParam(value = "cantidad", defaultValue = "10") int cantidad,
                                             @RequestParam(value = "origen", defaultValue = "0") int origen) throws Exception {
        String user = request.getHeader(JWTServiceImpl.USER_STRING);
        log.info(request.getContextPath());
        MtSolutionsResponse resp = new MtSolutionsResponse();
        resp.setSuccess(true);
        resp.setData(this.service.buscarUsuario(filtro, user, cantidad, origen));
        resp.setMessage("OK");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listaRoles(HttpServletRequest request) throws Exception {
        String user = request.getHeader(JWTServiceImpl.USER_STRING);
        log.info(request.getContextPath());
        MtSolutionsResponse resp = new MtSolutionsResponse();
        resp.setSuccess(true);
        resp.setData(this.service.listadoRoles(user));
        resp.setMessage("OK");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/perfiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listaPerfiles(HttpServletRequest request) throws Exception {
        String user = request.getHeader(JWTServiceImpl.USER_STRING);
        log.info(request.getContextPath());
        MtSolutionsResponse resp = new MtSolutionsResponse();
        resp.setSuccess(true);
        resp.setData(this.service.listadoPerfiles(user));
        resp.setMessage("OK");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/funcionalidades", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listaFuncionalidades(HttpServletRequest request) throws Exception {
        String user = request.getHeader(JWTServiceImpl.USER_STRING);
        log.info(request.getContextPath());
        MtSolutionsResponse resp = new MtSolutionsResponse();
        resp.setSuccess(true);
        resp.setData(this.service.listadoFuncionalidades(user));
        resp.setMessage("OK");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping(value="/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearUsuario(HttpServletRequest request,
                                          @RequestBody UsuarioDTO dto) throws Exception {
        String user = request.getHeader(JWTServiceImpl.USER_STRING);
        log.info(request.getContextPath());
        MtSolutionsResponse resp = new MtSolutionsResponse();
        resp.setSuccess(true);
        resp.setData(this.service.crearUsuario(dto, user));
        resp.setMessage("OK");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping(value="/editar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearUsuario(HttpServletRequest request,
                                          @RequestBody UsuarioDTO dto) throws Exception {
        String user = request.getHeader(JWTServiceImpl.USER_STRING);
        log.info(request.getContextPath());
        MtSolutionsResponse resp = new MtSolutionsResponse();
        resp.setSuccess(true);
        resp.setData(this.service.crearUsuario(dto, user));
        resp.setMessage("OK");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
