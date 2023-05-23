package br.com.vibbra.notificationservice.exceptions;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import org.springframework.http.HttpStatus;

public class GapBetweenDatesTooLongErrorException extends HttpException {

    private static final ErrorMessage ERROR_MESSAGE = ErrorMessage.GAP_BETWEEN_DATES_TO_LONG;
    private final SimpleError simpleError;

    public GapBetweenDatesTooLongErrorException() {
        super(ERROR_MESSAGE.getMessage());
        simpleError = SimpleError.builder()
                .message(ERROR_MESSAGE.getMessage())
                .code(ERROR_MESSAGE.getCode())
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
