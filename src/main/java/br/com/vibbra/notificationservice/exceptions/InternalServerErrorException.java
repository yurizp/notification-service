package br.com.vibbra.notificationservice.exceptions;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends HttpException {

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
    public SimpleError getError() {
        return simpleError;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
