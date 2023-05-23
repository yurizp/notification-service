package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.controller.response.notificationsettings.NotificationResponse;
import br.com.vibbra.notificationservice.db.entity.notification.email.EmailEntity;
import br.com.vibbra.notificationservice.db.entity.notification.sms.SmsEntity;
import br.com.vibbra.notificationservice.db.entity.notification.webpush.WebpushEntity;

public class NotificationResponseMapper {

    public static NotificationResponse create(WebpushEntity webpushEntity) {
        return NotificationResponse.builder()
                .settings(WebpushesResponseMapper.create(webpushEntity))
                .build();
    }

    public static NotificationResponse create(EmailEntity webpushEntity) {
        return NotificationResponse.builder()
                .settings(EmailResponseMapper.create(webpushEntity))
                .build();
    }

    public static NotificationResponse create(SmsEntity smsEntity) {
        return NotificationResponse.builder()
                .settings(SmsResponseMapper.create(smsEntity))
                .build();
    }
}
