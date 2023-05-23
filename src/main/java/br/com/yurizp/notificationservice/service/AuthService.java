package br.com.yurizp.notificationservice.service;

import br.com.yurizp.notificationservice.controller.request.authentication.AuthRequest;
import br.com.yurizp.notificationservice.dto.User;
import br.com.yurizp.commons.authentication.dto.Token;
import br.com.yurizp.commons.authentication.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenService tokenService;
    private final UserService userService;


    public Token generateToken(AuthRequest authRequest) throws IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, NoSuchProviderException {
        log.info("[Criar Token] Gerando token para o user: {}", authRequest);
        User user = userService.findByEmailAndPassword(authRequest.getEmail(), authRequest.getPassword());
        Token jwtToken = tokenService.generateToken(user.getEmail(), Map.of("userId", user.getId()));
        log.info("[Criar Token] Token gerado com sucesso: {}", jwtToken);
        return jwtToken;
    }

    public Token decodeToken(final String token) throws IllegalBlockSizeException, BadPaddingException {
        return tokenService.decodeToken(token);
    }
}
