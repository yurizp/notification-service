package br.com.vibbra.notificationservice.controller;

import br.com.vibbra.notificationservice.controller.request.authentication.AuthRequest;
import br.com.vibbra.notificationservice.dto.JwtToken;
import br.com.vibbra.notificationservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/v1/login")
@RestController("AutenticaoApiV1")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Gestão de autenticação")
public class LoginController {

    private final AuthService authService;

    @PostMapping()
    @Operation(
            summary = "Api responsavel para gerar token JWT.",
            description = "Para gerar o token voce deve ter um usario valido.")
    public JwtToken login(@RequestBody @Validated AuthRequest userRequest) {
        log.info("[AuthController] Iniciado o processo de geração de token. Request:{}", userRequest);
        JwtToken token = authService.generateToken(userRequest);
        log.info("[AuthController] Finalizado o processo de geração de token. Response:{}", token);
        return token;
    }
}
