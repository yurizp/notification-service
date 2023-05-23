package br.com.yurizp.notificationservice.controller;

import br.com.yurizp.notificationservice.controller.request.notificationsettings.NotificationRequest;
import br.com.yurizp.notificationservice.controller.request.sendnotification.SendNotificationRequest;
import br.com.yurizp.notificationservice.controller.response.notificationsettings.NotificationResponse;
import br.com.yurizp.notificationservice.controller.response.sendnotification.HistoryNotification;
import br.com.yurizp.notificationservice.dto.NotificationConfigResponse;
import br.com.yurizp.notificationservice.enums.Channel;
import br.com.yurizp.notificationservice.facade.NotificationFacade;
import br.com.yurizp.notificationservice.service.AuthService;
import br.com.yurizp.commons.authentication.dto.Token;
import br.com.yurizp.commons.authentication.secured.Secured;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.time.LocalDate;

@Slf4j
@RequestMapping("/v1/apps")
@RestController("WebPushControllerV1")
@RequiredArgsConstructor
public class NotificationController {

    public final NotificationFacade facede;
    private final AuthService authService;

    @Secured
    @PostMapping("/{appId}/{channel}/settings")
    public void settings(
            @RequestHeader String authorization,
            @RequestBody @Validated NotificationRequest notification,
            @PathVariable Long appId,
            @PathVariable Channel channel) throws IllegalBlockSizeException, BadPaddingException {
        log.info("[NotificationController] Iniciada a configuração de notificação. Request:{}", notification);
        Token jwtToken = authService.decodeToken(authorization);
        facede.saveOrUpdateSettings(Long.parseLong(jwtToken.getParams().get("userToken").toString()), appId, channel, notification);
        log.info("[NotificationController] Finalizada a configuração de notificação. Request:{}", notification);
    }

    @Secured
    @PutMapping("/{appId}/{channel}/settings")
    public NotificationConfigResponse enableOrDisable(
            @RequestHeader String authorization, @PathVariable Long appId, @PathVariable Channel channel) throws IllegalBlockSizeException, BadPaddingException {
        log.info(
                "[NotificationController] Iniciada a configuração de notificação. AppId:{} Channel: {}",
                appId,
                channel);
        Token jwtToken = authService.decodeToken(authorization);
        NotificationConfigResponse notificationConfigResponse =
                facede.enableOrDisableNotification(Long.parseLong(jwtToken.getParams().get("userToken").toString()), appId, channel);
        log.info(
                "[NotificationController] Finalizada a configuração de notificação. AppId:{} Channel: {} Response:{}",
                appId,
                channel,
                notificationConfigResponse);
        return notificationConfigResponse;
    }

    @Secured
    @GetMapping("/{appId}/{channel}/settings")
    public NotificationResponse findConfig(
            @RequestHeader String authorization, @PathVariable Long appId, @PathVariable Channel channel) throws IllegalBlockSizeException, BadPaddingException {
        log.info("[NotificationController] Iniciada a busca das configs por AppId:{} Channel: {}", appId, channel);
        Token jwtToken = authService.decodeToken(authorization);
        NotificationResponse notificationResponse =
                facede.findConfig(Long.parseLong(jwtToken.getParams().get("userToken").toString()), appId, channel);
        log.info(
                "[NotificationController] Finalizada a busca das configs por AppId:{} Channel: {} Response:{}",
                appId,
                channel,
                notificationResponse);
        return notificationResponse;
    }

    @Secured
    @PostMapping("/{appId}/{channel}/notifications")
    public void findConfig(
            @RequestHeader String authorization,
            @PathVariable Long appId,
            @PathVariable Channel channel,
            @RequestBody SendNotificationRequest sendNotificationRequest) throws IllegalBlockSizeException, BadPaddingException {
        log.info("[NotificationController] Iniciada a busca das configs por AppId:{} Channel: {}", appId, channel);
        Token jwtToken = authService.decodeToken(authorization);
        facede.sendNotification(Long.parseLong(jwtToken.getParams().get("userToken").toString()), appId, channel, sendNotificationRequest);
        log.info(
                "[NotificationController] Finalizada a busca das configs por AppId:{} Channel: {} Response:{}",
                appId,
                channel);
    }

    @Secured
    @GetMapping("/{appId}/{channel}/notifications/history")
    public HistoryNotification getHistory(
            @RequestParam @DateTimeFormat(pattern = "yyyyMMdd") LocalDate initdate,
            @RequestParam @DateTimeFormat(pattern = "yyyyMMdd") LocalDate enddate,
            @RequestHeader String authorization,
            @PathVariable Long appId,
            @PathVariable Channel channel) throws IllegalBlockSizeException, BadPaddingException {
        log.info("[NotificationController] Iniciada a busca das configs por AppId:{} Channel: {}", appId, channel);
        Token jwtToken = authService.decodeToken(authorization);
        HistoryNotification notificationHistory =
                facede.getNotificationHistory(Long.parseLong(jwtToken.getParams().get("userToken").toString()), appId, channel, initdate, enddate);
        log.info(
                "[NotificationController] Finalizada a busca das configs por AppId:{} Channel: {} Response:{}",
                appId,
                channel,
                notificationHistory);
        return notificationHistory;
    }
}
