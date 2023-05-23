package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.db.entity.AppEntity;
import br.com.vibbra.notificationservice.db.entity.NotificationHistoryEntity;
import br.com.vibbra.notificationservice.enums.Channel;
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
