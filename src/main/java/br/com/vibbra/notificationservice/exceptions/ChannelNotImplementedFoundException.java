package br.com.vibbra.notificationservice.exceptions;

import org.springframework.http.HttpStatus;

public class ChannelNotImplementedFoundException extends HttpException {

    private final ErrorMessage ERROR = ErrorMessage.CHANNEL_NOT_IMPLEMENTED;
    private final SimpleError simpleError;

    public ChannelNotImplementedFoundException() {
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
        return HttpStatus.NOT_IMPLEMENTED;
    }
}
