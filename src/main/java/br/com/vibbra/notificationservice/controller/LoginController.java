package br.com.vibbra.notificationservice.controller;

import br.com.vibbra.notificationservice.controller.request.authentication.UserRequest;
import br.com.vibbra.notificationservice.dto.JwtToken;
import br.com.vibbra.notificationservice.dto.User;
import br.com.vibbra.notificationservice.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/login")
@RestController("AutenticaoApi")
@RequiredArgsConstructor
public class LoginController {

    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    @PostMapping()
    public JwtToken login(@RequestBody UserRequest userRequest) {
        log.info("[AuthController] Iniciado o processo de geração de token. Request:{}", userRequest);
        User userDto = objectMapper.convertValue(userRequest, User.class);
        JwtToken token = jwtService.generateToken(userDto);
        log.info("[AuthController] Finalizado o processo de geração de token. Response:{}", token);
        return token;
    }
}
