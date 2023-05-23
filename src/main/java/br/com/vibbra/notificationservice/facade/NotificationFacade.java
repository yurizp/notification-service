package br.com.vibbra.notificationservice.facade;

import br.com.vibbra.notificationservice.controller.request.notificationsettings.NotificationRequest;
import br.com.vibbra.notificationservice.controller.request.sendnotification.SendNotificationRequest;
import br.com.vibbra.notificationservice.controller.response.notificationsettings.NotificationResponse;
import br.com.vibbra.notificationservice.controller.response.sendnotification.HistoryNotification;
import br.com.vibbra.notificationservice.dto.NotificationConfigResponse;
import br.com.vibbra.notificationservice.enums.Channel;
import br.com.vibbra.notificationservice.exceptions.ChannelNotImplementedFoundException;
import br.com.vibbra.notificationservice.strategy.NotificationStrategy;
import br.com.vibbra.notificationservice.strategy.ValidationNotificationStrategy;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationFacade {

    private final Map<Channel, NotificationStrategy> notifications;
    private final Map<Channel, ValidationNotificationStrategy> validations;

    public void saveOrUpdateSettings(Long userId, Long appId, Channel channel, NotificationRequest notification) {
        log.info("[NotificationFacade] Salvando/Atualizando configurações para o canal {}", channel);
        NotificationStrategy notificationService = getNotificationService(channel);
        Optional<ValidationNotificationStrategy> validationService = Optional.ofNullable(validations.get(channel));

        if (!validations.isEmpty()) {
            log.info("[Validacao] Encontrada estrategia de validação {}", notification.getSettings());
            validationService.get().validate(userId, appId, channel, notification);
            log.info("[Validacao] Valido para seguir no fluxo. {}", notification.getSettings());
        }

        notificationService.saveOrUpdateSettings(userId, appId, channel, notification);
        log.info(
                "[NotificationFacade] Configurações salvas/atualizadas para o canal {} configuracoes:{}",
                channel,
                notification);
    }

    public NotificationConfigResponse enableOrDisableNotification(Long userId, Long appId, Channel channel) {
        log.info("[NotificationFacade] Habilitando/Desabilitando notificação para o canal {}", channel);
        NotificationStrategy notificationService = getNotificationService(channel);
        NotificationConfigResponse notificationConfigResponse =
                notificationService.enableOrDisableNotification(userId, appId, channel);
        log.info("[NotificationFacade] Notificação/Habilitada ou desabilitada para o canal {}", channel);
        return notificationConfigResponse;
    }

    public NotificationResponse findConfig(Long userId, Long appId, Channel channel) {
        log.info(
                "[NotificationFacade] Buscando configurações para o canal {} userId:{} appId:{}",
                channel,
                userId,
                appId);
        NotificationStrategy notificationService = getNotificationService(channel);
        NotificationResponse response = notificationService.findConfig(userId, appId, channel);
        log.info("[NotificationFacade] Configurações encontradas para o canal {} configurações:{}", channel, response);
        return response;
    }

    public void sendNotification(
            Long userId, Long appId, Channel channel, SendNotificationRequest sendNotificationRequest) {
        log.info(
                "[NotificationFacade] Buscando configurações para o canal {} userId:{} appId:{}",
                channel,
                userId,
                appId);
        NotificationStrategy notificationService = getNotificationService(channel);
        notificationService.sendNotification(userId, appId, channel, sendNotificationRequest);
        log.info("[NotificationFacade] Configurações encontradas para o canal {} configurações:{}", channel);
    }

    public HistoryNotification getNotificationHistory(
            Long userId, Long appId, Channel channel, LocalDate startDate, LocalDate endTade) {
        log.info(
                "[NotificationFacade] Buscando configurações para o canal {} userId:{} appId:{}",
                channel,
                userId,
                appId);
        NotificationStrategy notificationService = getNotificationService(channel);
        HistoryNotification notifications =
                notificationService.getNotifications(userId, appId, channel, startDate, endTade);
        log.info(
                "[NotificationFacade] Configurações encontradas para o canal {} configurações:{}",
                channel,
                notifications);
        return notifications;
    }

    private NotificationStrategy getNotificationService(Channel channel) {
        log.info("[NotificationFacade] Buscando estrategia de notificação para o canal {}", channel);
        return Optional.ofNullable(notifications.get(channel))
                .orElseThrow(() -> new ChannelNotImplementedFoundException());
    }
}
