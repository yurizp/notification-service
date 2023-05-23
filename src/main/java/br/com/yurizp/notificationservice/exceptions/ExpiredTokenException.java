package br.com.yurizp.notificationservice.exceptions;

import br.com.yurizp.commons.error.exceptions.base.BaseError;
import br.com.yurizp.commons.error.exceptions.base.HttpException;
import br.com.yurizp.commons.tostring.Objects;
import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends HttpException {

    private final ErrorMessage ERROR = ErrorMessage.EXPIRED_TOKEN;
    private final BaseError simpleError;

    public ExpiredTokenException() {
        super(ErrorMessage.EXPIRED_TOKEN.getMessage());
        simpleError = BaseError.builder()
                .message(ERROR.getMessage())
                .code(ERROR.getCode())
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
        return HttpStatus.FORBIDDEN;
    }
}
