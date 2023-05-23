package br.com.vibbra.notificationservice.service;

import br.com.vibbra.notificationservice.controller.request.authentication.AuthRequest;
import br.com.vibbra.notificationservice.dto.JwtToken;
import br.com.vibbra.notificationservice.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserService userService;

    public JwtToken generateToken(AuthRequest authRequest) {
        log.info("[Criar Token] Gerando token para o user: {}", authRequest);
        User user = userService.findByEmailAndPassword(authRequest.getEmail(), authRequest.getPassword());
        JwtToken jwtToken = jwtService.generateToken(user);
        log.info("[Criar Token] Token gerado com sucesso: {}", jwtToken);
        return jwtToken;
    }

    public JwtToken decodeToken(final String token) {
        return jwtService.decodeToken(token);
    }
}
