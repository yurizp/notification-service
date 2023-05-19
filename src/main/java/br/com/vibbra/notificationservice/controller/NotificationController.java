package br.com.vibbra.notificationservice.controller;

import br.com.vibbra.notificationservice.config.auth.Secured;
import br.com.vibbra.notificationservice.controller.request.notification.NotificationRequest;
import br.com.vibbra.notificationservice.controller.response.notification.NotificationResponse;
import br.com.vibbra.notificationservice.dto.JwtToken;
import br.com.vibbra.notificationservice.dto.NotificationConfigResponse;
import br.com.vibbra.notificationservice.enums.Channel;
import br.com.vibbra.notificationservice.facade.NotificationFacade;
import br.com.vibbra.notificationservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/v1/apps")
@RestController("WebPushControllerV1")
@RequiredArgsConstructor
@Tag(name = "Notificações", description = "Gestão de WebPush")
public class NotificationController {

    public final NotificationFacade facede;
    private final AuthService authService;

    @Secured
    @PostMapping("/{appId}/{channel}/settings")
    @Operation(
            summary = "Api responsavel por configurar um WebPush vinculado ao usuario.",
            description = "Para gerar o App voce deve ter um token valido.")
    public void settings(
            @RequestHeader String authorization,
            @RequestBody @Validated NotificationRequest notification,
            @PathVariable Long appId,
            @PathVariable Channel channel) {
        JwtToken jwtToken = authService.decodeToken(authorization);
        facede.saveOrUpdateSettings(jwtToken.getUser().getId(), appId, channel, notification);
    }

    @Secured
    @PutMapping("/{appId}/{channel}/settings")
    @Operation(
            summary = "Api responsavel por configurar um WebPush vinculado ao usuario.",
            description = "Para gerar o App voce deve ter um token valido.")
    public NotificationConfigResponse enableOrDisable(
            @RequestHeader String authorization, @PathVariable Long appId, @PathVariable Channel channel) {
        JwtToken jwtToken = authService.decodeToken(authorization);
        NotificationConfigResponse notificationConfigResponse =
                facede.enableOrDisableNotification(jwtToken.getUser().getId(), appId, channel);
        return notificationConfigResponse;
    }

    @Secured
    @GetMapping("/{appId}/{channel}/settings")
    @Operation(
            summary = "Api responsavel por configurar um WebPush vinculado ao usuario.",
            description = "Para gerar o App voce deve ter um token valido.")
    public NotificationResponse findConfig(
            @RequestHeader String authorization, @PathVariable Long appId, @PathVariable Channel channel) {
        JwtToken jwtToken = authService.decodeToken(authorization);
        NotificationResponse notificationResponse =
                facede.findConfig(jwtToken.getUser().getId(), appId, channel);
        return notificationResponse;
    }
}
