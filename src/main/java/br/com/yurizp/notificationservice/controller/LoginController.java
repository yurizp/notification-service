package br.com.yurizp.notificationservice.controller;

import br.com.yurizp.notificationservice.controller.request.authentication.AuthRequest;
import br.com.yurizp.notificationservice.service.AuthService;
import br.com.yurizp.commons.authentication.dto.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@Slf4j
@RequestMapping("/v1/login")
@RestController("AutenticaoApiV1")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping()
    public Token login(@RequestBody @Validated AuthRequest userRequest) throws IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, NoSuchProviderException {
        log.info("[AuthController] Iniciado o processo de geração de token. Request:{}", userRequest);
        Token token = authService.generateToken(userRequest);
        log.info("[AuthController] Finalizado o processo de geração de token. Response:{}", token);
        return token;
    }
}
