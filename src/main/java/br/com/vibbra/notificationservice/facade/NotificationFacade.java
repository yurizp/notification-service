package br.com.vibbra.notificationservice.facade;

import br.com.vibbra.notificationservice.controller.request.notification.NotificationRequest;
import br.com.vibbra.notificationservice.controller.response.notification.NotificationResponse;
import br.com.vibbra.notificationservice.dto.NotificationConfigResponse;
import br.com.vibbra.notificationservice.enums.Channel;
import br.com.vibbra.notificationservice.exceptions.ChannelNotImplementedFoundException;
import br.com.vibbra.notificationservice.strategy.NotificationStrategy;
import br.com.vibbra.notificationservice.strategy.ValidationNotificationStrategy;
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
        NotificationStrategy notificationService = getNotificationService(channel);
        Optional<ValidationNotificationStrategy> validationService = Optional.ofNullable(validations.get(channel));

        if (!validations.isEmpty()) {
            log.info("[Validacao] Encontrada estrategia de validação {}", notification.getSettings());
            validationService.get().validate(userId, appId, channel, notification);
            log.info("[Validacao] Valido para seguir no fluxo. {}", notification.getSettings());
        }

        notificationService.saveOrUpdateSettings(userId, appId, channel, notification);
    }

    public NotificationConfigResponse enableOrDisableNotification(Long userId, Long appId, Channel channel) {
        NotificationStrategy notificationService = getNotificationService(channel);
        return notificationService.enableOrDisableNotification(userId, appId, channel);
    }

    public NotificationResponse findConfig(Long userId, Long appId, Channel channel) {
        NotificationStrategy notificationService = getNotificationService(channel);
        return notificationService.findConfig(userId, appId, channel);
    }

    private NotificationStrategy getNotificationService(Channel channel) {
        return Optional.ofNullable(notifications.get(channel))
                .orElseThrow(() -> new ChannelNotImplementedFoundException());
    }
}
