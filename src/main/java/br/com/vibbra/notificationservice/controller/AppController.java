package br.com.vibbra.notificationservice.controller;

import br.com.vibbra.notificationservice.config.auth.Secured;
import br.com.vibbra.notificationservice.controller.request.app.CreateAppRequest;
import br.com.vibbra.notificationservice.controller.response.UserResponse;
import br.com.vibbra.notificationservice.controller.response.app.AppResponse;
import br.com.vibbra.notificationservice.dto.JwtToken;
import br.com.vibbra.notificationservice.dto.User;
import br.com.vibbra.notificationservice.mapper.AppResponseMapper;
import br.com.vibbra.notificationservice.mapper.UserResposeMapper;
import br.com.vibbra.notificationservice.service.AppService;
import br.com.vibbra.notificationservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping("/v1/apps")
@RestController("AppControllerV1")
@RequiredArgsConstructor
@Tag(name = "Aplicativos", description = "Gestão de App`s")
public class AppController {

    private final AppService appService;
    private final AuthService authService;

    @Secured
    @PostMapping
    @Operation(
            summary = "Api responsavel por criar um App vinculado ao usuario.",
            description = "Para gerar o App voce deve ter um token valido."
    )
    public AppResponse createApp(
            @RequestHeader String authorization, @RequestBody @Validated CreateAppRequest createApp) {
        log.info("[CreateApp] Iniciando o processo de criação de aplicativo {}", createApp);
        JwtToken jwtToken = authService.decodeToken(authorization);
        AppResponse response = appService.createApp(createApp, jwtToken.getUser().getId());
        log.info("[CreateApp] Finalizado o processo de criação de aplicativo {}", response);
        return response;
    }

    @Secured
    @GetMapping("/{id}")
    @Operation(
            summary = "Api responsavel por buscar um App por id.",
            description = "Para gerar o App voce deve ter um token valido."
    )
    public AppResponse findById(
            @RequestHeader String authorization, @PathVariable Long id) {
        log.info("[CreateApp] Iniciando a busca de um aplicativo {}", id);
        JwtToken jwtToken = authService.decodeToken(authorization);
        AppResponse response = appService.findAppById(id, jwtToken.getUser().getId());
        log.info("[CreateApp] Finalizada a busca de um aplicativo {}", response);
        return response;
    }

}
