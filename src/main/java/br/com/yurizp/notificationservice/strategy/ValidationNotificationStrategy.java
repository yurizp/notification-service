package br.com.yurizp.notificationservice.strategy;

import br.com.yurizp.notificationservice.controller.request.notificationsettings.NotificationRequest;
import br.com.yurizp.notificationservice.enums.Channel;

public interface ValidationNotificationStrategy {

    public Channel getChannel();

    public void validate(Long userId, Long appId, Channel channel, NotificationRequest notification);
}
