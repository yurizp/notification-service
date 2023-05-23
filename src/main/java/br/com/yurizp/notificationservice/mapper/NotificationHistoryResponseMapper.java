package br.com.yurizp.notificationservice.mapper;

import br.com.yurizp.notificationservice.controller.response.sendnotification.NotificationHistoryResponse;
import br.com.yurizp.notificationservice.db.entity.NotificationHistoryEntity;

public class NotificationHistoryResponseMapper {
    public static NotificationHistoryResponse create(NotificationHistoryEntity notificationHistoryEntity) {
        return NotificationHistoryResponse.builder()
                .notificationId(notificationHistoryEntity.getId())
                .sendDate(notificationHistoryEntity.getCreatedAt())
                .build();
    }
}
