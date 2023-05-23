package br.com.vibbra.notificationservice.exceptions;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import org.springframework.http.HttpStatus;

public class ValidationException extends HttpException {

    private final ErrorMessage ERROR = ErrorMessage.VALIDATION_ERROR_DEFAULT;
    private final SimpleError simpleError;

    public ValidationException(final Object details) {
        super(ErrorMessage.EXPIRED_TOKEN.getMessage());
        simpleError = SimpleError.builder()
                .message(ERROR.getMessage())
                .code(ERROR.getCode())
                .details(details)
                .build();
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }

    @Override
    public SimpleError getError() {
        return simpleError;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
