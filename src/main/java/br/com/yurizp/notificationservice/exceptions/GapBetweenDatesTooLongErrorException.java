package br.com.yurizp.notificationservice.exceptions;

import br.com.yurizp.commons.error.exceptions.base.BaseError;
import br.com.yurizp.commons.error.exceptions.base.HttpException;
import br.com.yurizp.commons.tostring.Objects;
import org.springframework.http.HttpStatus;

public class GapBetweenDatesTooLongErrorException extends HttpException {

    private static final ErrorMessage ERROR_MESSAGE = ErrorMessage.GAP_BETWEEN_DATES_TO_LONG;
    private final BaseError simpleError;

    public GapBetweenDatesTooLongErrorException() {
        super(ERROR_MESSAGE.getMessage());
        simpleError = BaseError.builder()
                .message(ERROR_MESSAGE.getMessage())
                .code(ERROR_MESSAGE.getCode())
                .build();
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }

    @Override
    public BaseError getError() {
        return simpleError;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
