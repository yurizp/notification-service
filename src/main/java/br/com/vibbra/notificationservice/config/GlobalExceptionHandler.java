package br.com.vibbra.notificationservice.config;

import br.com.vibbra.notificationservice.exceptions.ExpiredTokenException;
import br.com.vibbra.notificationservice.exceptions.HttpException;
import br.com.vibbra.notificationservice.exceptions.InternalServerErrorException;
import br.com.vibbra.notificationservice.exceptions.ValidationException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity internalServerError(Exception e) {
        InternalServerErrorException exception = new InternalServerErrorException();
        log.error("Ocorreu um erro não esperado. Erro de saida:{} Exception:{}", exception.toString(), e.getCause());
        return ResponseEntity.status(exception.getStatus()).body(exception.getError());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity validationError(MethodArgumentNotValidException e) {
        ValidationException exception = new ValidationException(e.getDetailMessageArguments());
        log.error("Ocorreu um erro de validação. Erro de saida:  {} Exception: {}", exception, e);
        return ResponseEntity.status(exception.getStatus()).body(exception.getError());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity validationError(ExpiredJwtException e) {
        ExpiredTokenException exception = new ExpiredTokenException();
        log.error("Token informado é invalido. Erro de saida:  {} Exception: {}", exception, e);
        return ResponseEntity.status(exception.getStatus()).body(exception.getError());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity httpExceptionHandler(HttpException e) {
        log.error("O fluxo retornou um erro {} ErroCompleto:{}", e.getError(), e.toString());
        return ResponseEntity.status(e.getStatus()).body(e.getError());
    }
}
