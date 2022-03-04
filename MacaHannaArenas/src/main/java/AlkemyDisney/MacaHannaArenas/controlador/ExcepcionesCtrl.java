package AlkemyDisney.MacaHannaArenas.controlador;



import AlkemyDisney.MacaHannaArenas.dto.APIErrorDTO;
import AlkemyDisney.MacaHannaArenas.excepcion.ParamNoEncontrado;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExcepcionesCtrl extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ParamNoEncontrado.class})
    protected ResponseEntity<Object> handleParamNotFound(RuntimeException ex, WebRequest request) {

        APIErrorDTO errorDTO = new APIErrorDTO(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Arrays.asList("Parametro no encontrado"));

        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> errores = new ArrayList<>();

        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errores.add(error.getField() + ":" + error.getDefaultMessage());
        }
        for (ObjectError error : exception.getBindingResult().getGlobalErrors()) {
            errores.add(error.getObjectName() + ":" + error.getDefaultMessage());
        }

        APIErrorDTO apiError = new APIErrorDTO(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), errores);

        return handleExceptionInternal(exception, apiError, headers, apiError.getStatus(), request);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {

        String bodyOfResponse = "Debe ser especifico de la aplicacion";

        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
