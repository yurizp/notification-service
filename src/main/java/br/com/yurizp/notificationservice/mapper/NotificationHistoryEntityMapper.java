package br.com.yurizp.notificationservice.mapper;

import br.com.yurizp.notificationservice.db.entity.AppEntity;
import br.com.yurizp.notificationservice.db.entity.NotificationHistoryEntity;
import br.com.yurizp.notificationservice.enums.Channel;
import java.time.LocalDate;

public class NotificationHistoryEntityMapper {
    public static NotificationHistoryEntity create(AppEntity app, Channel channel) {
        return NotificationHistoryEntity.builder()
                .app(app)
                .channel(channel)
                .createdAt(LocalDate.now())
                .build();
    }
}
