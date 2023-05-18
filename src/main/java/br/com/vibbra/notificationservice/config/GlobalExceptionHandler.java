package br.com.vibbra.notificationservice.config;

import br.com.vibbra.notificationservice.exceptions.ExpiredTokenException;
import br.com.vibbra.notificationservice.exceptions.HttpException;
import br.com.vibbra.notificationservice.exceptions.InternalServerErrorException;
import br.com.vibbra.notificationservice.exceptions.ValidationException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity notExpectedError(Exception e) {
        InternalServerErrorException error = new InternalServerErrorException(e, e.getMessage());
        return ResponseEntity.status(error.getStatus()).body(error.getError());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity validationError(MethodArgumentNotValidException e) {
        ValidationException exception = new ValidationException(e.getDetailMessageArguments());
        return ResponseEntity.status(exception.getStatus()).body(exception.getError());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity validationError(ExpiredJwtException e) {
        ExpiredTokenException expiredTokenException = new ExpiredTokenException();
        return ResponseEntity.status(expiredTokenException.getStatus()).body(expiredTokenException.getError());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity handleMethodArgumentNotValidException(HttpException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getError());
    }
}
