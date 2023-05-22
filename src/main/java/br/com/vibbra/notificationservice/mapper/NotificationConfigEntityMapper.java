package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.db.entity.AppEntity;
import br.com.vibbra.notificationservice.db.entity.NotificationConfigEntity;
import br.com.vibbra.notificationservice.enums.Channel;

public class NotificationConfigEntityMapper {

    public static NotificationConfigEntity create(AppEntity app, Channel channel) {
        return NotificationConfigEntity.builder()
                .app(app)
                .channel(channel)
                .enabled(false)
                .build();
    }
}
