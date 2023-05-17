package br.com.vibbra.notificationservice.exceptions;

import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends HttpException {

    private final ErrorMessage ERROR = ErrorMessage.EXPIRED_TOKEN;
    private final SimpleError simpleError;

    public ExpiredTokenException() {
        super(ErrorMessage.EXPIRED_TOKEN.getMessage());
        simpleError = SimpleError.builder()
                .message(ERROR.getMessage())
                .code(ERROR.getCode())
                .build();
    }

    @Override
    public SimpleError getError() {
        return simpleError;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
