package br.com.yurizp.notificationservice.mapper;

import br.com.yurizp.notificationservice.db.entity.AppEntity;
import br.com.yurizp.notificationservice.db.entity.NotificationConfigEntity;
import br.com.yurizp.notificationservice.enums.Channel;

public class NotificationConfigEntityMapper {

    public static NotificationConfigEntity create(AppEntity app, Channel channel) {
        return NotificationConfigEntity.builder()
                .app(app)
                .channel(channel)
                .enabled(true)
                .build();
    }
}
