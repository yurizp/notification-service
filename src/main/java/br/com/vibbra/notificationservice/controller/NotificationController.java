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
    @Operation(summary = "Api responsavel por configurar um canal de notificação.")
    public void settings(
            @RequestHeader String authorization,
            @RequestBody @Validated NotificationRequest notification,
            @PathVariable Long appId,
            @PathVariable Channel channel) {
        log.info("[NotificationController] Iniciada a configuração de notificação. Request:{}", notification);
        JwtToken jwtToken = authService.decodeToken(authorization);
        facede.saveOrUpdateSettings(jwtToken.getUser().getId(), appId, channel, notification);
        log.info("[NotificationController] Finalizada a configuração de notificação. Request:{}", notification);
    }

    @Secured
    @PutMapping("/{appId}/{channel}/settings")
    @Operation(summary = "Api responsavel por habilitar ou desabilitar notificação.")
    public NotificationConfigResponse enableOrDisable(
            @RequestHeader String authorization, @PathVariable Long appId, @PathVariable Channel channel) {
        log.info(
                "[NotificationController] Iniciada a configuração de notificação. AppId:{} Channel: {}",
                appId,
                channel);
        JwtToken jwtToken = authService.decodeToken(authorization);
        NotificationConfigResponse notificationConfigResponse =
                facede.enableOrDisableNotification(jwtToken.getUser().getId(), appId, channel);
        log.info(
                "[NotificationController] Finalizada a configuração de notificação. AppId:{} Channel: {} Response:{}",
                appId,
                channel,
                notificationConfigResponse);
        return notificationConfigResponse;
    }

    @Secured
    @GetMapping("/{appId}/{channel}/settings")
    @Operation(summary = "Api responsavel por buscar as configs por AppId.")
    public NotificationResponse findConfig(
            @RequestHeader String authorization, @PathVariable Long appId, @PathVariable Channel channel) {
        log.info("[NotificationController] Iniciada a busca das configs por AppId:{} Channel: {}", appId, channel);
        JwtToken jwtToken = authService.decodeToken(authorization);
        NotificationResponse notificationResponse =
                facede.findConfig(jwtToken.getUser().getId(), appId, channel);
        log.info(
                "[NotificationController] Finalizada a busca das configs por AppId:{} Channel: {} Response:{}",
                appId,
                channel,
                notificationResponse);
        return notificationResponse;
    }
}
