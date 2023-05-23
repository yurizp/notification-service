package br.com.vibbra.notificationservice.strategy;

import br.com.vibbra.notificationservice.controller.request.notificationsettings.NotificationRequest;
import br.com.vibbra.notificationservice.controller.request.sendnotification.SendNotificationRequest;
import br.com.vibbra.notificationservice.controller.response.notificationsettings.NotificationResponse;
import br.com.vibbra.notificationservice.controller.response.sendnotification.HistoryNotification;
import br.com.vibbra.notificationservice.dto.NotificationConfigResponse;
import br.com.vibbra.notificationservice.enums.Channel;
import java.time.LocalDate;

public interface NotificationStrategy {

    Channel getChannel();

    void saveOrUpdateSettings(Long userId, Long appId, Channel channel, NotificationRequest notification);

    NotificationConfigResponse enableOrDisableNotification(Long userId, Long appId, Channel channel);

    NotificationResponse findConfig(Long userId, Long appId, Channel channel);

    void sendNotification(Long userId, Long appId, Channel channel, SendNotificationRequest sendNotificationRequest);

    HistoryNotification getNotifications(
            Long userId, Long appId, Channel channel, LocalDate startDate, LocalDate endDate);
}
