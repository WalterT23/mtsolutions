package proyecto2.mtsolutions.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import proyecto2.mtsolutions.dto.MtSolutionsResponse;
import proyecto2.mtsolutions.exceptions.ServiceException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ControllerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseEntity<?> handleException(ServiceException se){
        log.info("Error en el handler: {}" , se.getErrorCode().name()
                .concat(" - ")
                .concat(se.getMessage()));
        MtSolutionsResponse err = new MtSolutionsResponse();
        switch (se.getErrorCode()){
            case CUSTOM_ERROR: {
                err.setMessage(se.getMessage());
                return new ResponseEntity<>(err , HttpStatus.BAD_REQUEST);
            }
            case NO_DATA_FOUND: {
                err.setMessage(se.getMessage());
                return new ResponseEntity<>(err ,HttpStatus.NOT_FOUND);
            }
            case INTERNAL_ERROR:
            case DATABASE_ERROR: {
                err.setMessage("Ocurrió un error interno, la transacción no se completó");
                return new ResponseEntity<>(err ,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            case UNAVAILABLE: {
                err.setMessage("El servicio no está disponible en este momento");
                return new ResponseEntity<>(err, HttpStatus.SERVICE_UNAVAILABLE);
            }
            case UNAUTHORIZED: {
                err.setMessage("Recurso protegido, acceso denegado");
                return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
            }
            case FORBIDDEN: {
                err.setMessage("Exedió el tiempo de validez del token");
                return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
            }
            case NO_BODY_DATA: {
                err.setMessage("No se encuentra el cuerpo de la petición");
                return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
            }
            case PARAMETROS_FALTANTES: {
                err.setMessage("Faltan parametros para la consulta");
                return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
            }
            case ERROR_GENERAR_USUARIO: {
                err.setMessage("No fue posible crear el usuario");
                return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
            }
            case USUARIO_EXISTENTE: {
                err.setMessage("El usuario ya existe en el sistema");
                return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
            }
            case NO_TITULO_ROL: {
                err.setMessage("Falta el titulo del rol");
                return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
            }
            case NO_PERFIL_LIST: {
                err.setMessage("No se encontro la lista de perfiles");
                return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
            }
            default: {
                err.setMessage("Ocurrió un error inesperado, la transacción no se completó");
                return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError->fieldError.getDefaultMessage()).collect(Collectors.toList());
        //LOGGER.info("Validation error list : "+validationList);
        MtSolutionsResponse err = new MtSolutionsResponse();
        //ApiErrorVO apiErrorVO = new ApiErrorVO(errorMessage);
        err.setMessage(validationList.toString());
        //apiErrorVO.setErrorList(validationList);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

}
