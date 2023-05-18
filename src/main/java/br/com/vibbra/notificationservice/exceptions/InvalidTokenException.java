package br.com.vibbra.notificationservice.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends HttpException {

    private final ErrorMessage ERROR = ErrorMessage.INVALID_TOKEN;
    private final SimpleError simpleError;

    public InvalidTokenException() {
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
