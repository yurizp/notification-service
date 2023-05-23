package br.com.yurizp.notificationservice.exceptions;

import br.com.yurizp.commons.error.exceptions.base.BaseError;
import br.com.yurizp.commons.error.exceptions.base.HttpException;
import br.com.yurizp.commons.tostring.Objects;
import org.springframework.http.HttpStatus;

public class SettingsNotFoundException extends HttpException {

    private final ErrorMessage ERROR = ErrorMessage.APP_NOT_FOUND;
    private final BaseError simpleError;

    public SettingsNotFoundException() {
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
        return HttpStatus.NOT_FOUND;
    }
}
