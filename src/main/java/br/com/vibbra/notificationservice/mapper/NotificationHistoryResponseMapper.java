package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.controller.response.sendnotification.NotificationHistoryResponse;
import br.com.vibbra.notificationservice.db.entity.NotificationHistoryEntity;

public class NotificationHistoryResponseMapper {
    public static NotificationHistoryResponse create(NotificationHistoryEntity notificationHistoryEntity) {
        return NotificationHistoryResponse.builder()
                .notificationId(notificationHistoryEntity.getId())
                .sendDate(notificationHistoryEntity.getCreatedAt())
                .build();
    }
}
