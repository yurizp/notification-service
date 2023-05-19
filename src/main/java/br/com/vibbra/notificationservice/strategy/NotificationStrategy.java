package br.com.vibbra.notificationservice.strategy;

import br.com.vibbra.notificationservice.controller.request.notification.NotificationRequest;
import br.com.vibbra.notificationservice.controller.response.notification.NotificationResponse;
import br.com.vibbra.notificationservice.dto.NotificationConfigResponse;
import br.com.vibbra.notificationservice.enums.Channel;

public interface NotificationStrategy {

    public Channel getChannel();

    void saveOrUpdateSettings(Long userId, Long appId, Channel channel, NotificationRequest notification);

    NotificationConfigResponse enableOrDisableNotification(Long userId, Long appId, Channel channel);

    NotificationResponse findConfig(Long userId, Long appId, Channel channel);
}
