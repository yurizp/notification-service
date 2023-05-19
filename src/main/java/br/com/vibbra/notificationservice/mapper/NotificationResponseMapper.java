package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.controller.response.notification.NotificationResponse;
import br.com.vibbra.notificationservice.db.entity.notification.webpush.WebpushEntity;

public class NotificationResponseMapper {

    public static NotificationResponse create(WebpushEntity webpushEntity) {
        return NotificationResponse.builder()
                .settings(WebpushesResponseMapper.create(webpushEntity))
                .build();
    }
}
