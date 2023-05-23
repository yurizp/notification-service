package br.com.yurizp.notificationservice.controller;

import br.com.yurizp.notificationservice.controller.request.app.CreateAppRequest;
import br.com.yurizp.notificationservice.controller.response.app.AppResponse;
import br.com.yurizp.notificationservice.service.AppService;
import br.com.yurizp.notificationservice.service.AuthService;
import br.com.yurizp.commons.authentication.dto.Token;
import br.com.yurizp.commons.authentication.secured.Secured;
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

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

@Slf4j
@RequestMapping("/v1/apps")
@RestController("AppControllerV1")
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;
    private final AuthService authService;

    @Secured
    @PostMapping
    public AppResponse createApp(
            @RequestHeader String authorization, @RequestBody @Validated CreateAppRequest createApp) throws IllegalBlockSizeException, BadPaddingException {
        log.info("[CreateApp] Iniciando o processo de criação de aplicativo {}", createApp);
        Token jwtToken = authService.decodeToken(authorization);
        AppResponse response =
                appService.createApp(createApp, Long.parseLong(jwtToken.getParams().get("userId").toString()));
        log.info("[CreateApp] Finalizado o processo de criação de aplicativo {}", response);
        return response;
    }

    @Secured
    @GetMapping("/{id}")
    public AppResponse findById(@RequestHeader String authorization, @PathVariable Long id) throws IllegalBlockSizeException, BadPaddingException {
        log.info("[CreateApp] Iniciando a busca de um aplicativo {}", id);
        Token jwtToken = authService.decodeToken(authorization);
        AppResponse response = appService.findAppById(id, Long.parseLong(jwtToken.getParams().get("userId").toString()));
        log.info("[CreateApp] Finalizada a busca de um aplicativo {}", response);
        return response;
    }
}
