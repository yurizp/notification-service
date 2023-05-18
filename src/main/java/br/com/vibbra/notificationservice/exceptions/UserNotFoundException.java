package br.com.vibbra.notificationservice.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends HttpException {

    private final ErrorMessage ERROR = ErrorMessage.USER_NOT_FOUND;
    private final SimpleError simpleError;

    public UserNotFoundException() {
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
        return HttpStatus.NOT_FOUND;
    }
}
