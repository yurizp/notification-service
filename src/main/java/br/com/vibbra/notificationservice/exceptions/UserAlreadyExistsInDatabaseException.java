package br.com.vibbra.notificationservice.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsInDatabaseException extends HttpException {

    private final ErrorMessage ERROR = ErrorMessage.USER_ALREADY_EXISTS_IN_DATABASE;
    private final SimpleError simpleError;

    public UserAlreadyExistsInDatabaseException() {
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
        return HttpStatus.CONFLICT;
    }
}
