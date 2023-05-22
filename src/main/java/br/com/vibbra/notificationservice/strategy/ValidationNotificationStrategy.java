package br.com.vibbra.notificationservice.strategy;

import br.com.vibbra.notificationservice.controller.request.notification.NotificationRequest;
import br.com.vibbra.notificationservice.enums.Channel;

public interface ValidationNotificationStrategy {

    public Channel getChannel();

    public void validate(Long userId, Long appId, Channel channel, NotificationRequest notification);
}
