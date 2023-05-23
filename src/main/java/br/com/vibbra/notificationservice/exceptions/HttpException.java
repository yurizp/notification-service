package br.com.vibbra.notificationservice.exceptions;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import org.springframework.http.HttpStatus;

public abstract class HttpException extends RuntimeException {

    public HttpException(String message) {
        super(message);
    }

    public abstract SimpleError getError();

    public abstract HttpStatus getStatus();

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
