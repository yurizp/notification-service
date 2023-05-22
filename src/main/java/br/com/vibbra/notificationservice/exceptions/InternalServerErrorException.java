package br.com.vibbra.notificationservice.exceptions;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends HttpException {

    private static final ErrorMessage ERROR_MESSAGE = ErrorMessage.INTERNAL_SERVER_ERROR;
    private final SimpleError simpleError;

    public InternalServerErrorException(final Object details, final String errorMessage) {
        super(errorMessage);
        simpleError = SimpleError.builder()
                .message(errorMessage)
                .code("NTS-999")
                .details(details)
                .build();
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }

    public InternalServerErrorException() {
        super(ERROR_MESSAGE.getMessage());
        simpleError = SimpleError.builder()
                .message(ERROR_MESSAGE.getMessage())
                .code(ERROR_MESSAGE.getCode())
                .build();
    }

    @Override
    public SimpleError getError() {
        return simpleError;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
