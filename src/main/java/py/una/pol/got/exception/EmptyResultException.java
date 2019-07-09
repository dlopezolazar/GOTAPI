package py.una.pol.got.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class EmptyResultException extends RuntimeException {

    public EmptyResultException(String message) {
        super(message);
    }
}
