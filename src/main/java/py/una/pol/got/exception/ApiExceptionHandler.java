package py.una.pol.got.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> error = new HashMap<>();
        error.put("code","BAD_REQUEST");
        error.put("fields", ex.getBindingResult().getFieldErrors().stream().map(a -> {
            Map<String, String> m = new HashMap<>();
            m.put(a.getField(), a.getDefaultMessage());
            return m;
        }).collect(Collectors.toList()));
        logger.error(ex.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        Map<String, Object> error = new HashMap<>();
        error.put("code", "NOT_FOUND");
        error.put("message","No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL());
        logger.error("No handler found for {} {}", ex.getHttpMethod(), ex.getRequestURL());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(NotFoundException ex, WebRequest request) {
        Map<String, Object> error = new HashMap<>();
        error.put("code", "NOT_FOUND");
        error.put("message","No handler found for " + HttpMethod.GET + " " + request.getParameter(ex.getMessage()));
        logger.error("No handler found for {}", request.getParameter(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultException.class)
    public final ResponseEntity<Object> handleEmptyResult(){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("code", "NOT_FOUND");
        error.put("message","Error no manejado.");
        logger.error("Error al procesar petición: {} ", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
