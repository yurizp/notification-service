package br.com.yurizp.notificationservice.mapper;

import static org.junit.jupiter.api.Assertions.*;

import br.com.yurizp.notificationservice.db.entity.NotificationHistoryEntityStub;
import org.junit.jupiter.api.Test;

class NotificationHistoryResponseMapperTest {

    @Test
    public void shouldCreteNotificationHistoryResponse() {
        var notificationHistoryEntity = NotificationHistoryEntityStub.create();

        var notificationHistoryResponse = NotificationHistoryResponseMapper.create(notificationHistoryEntity);

        assertNotNull(notificationHistoryResponse);
        assertEquals(notificationHistoryEntity.getId(), notificationHistoryResponse.getNotificationId());
        assertEquals(notificationHistoryEntity.getCreatedAt(), notificationHistoryResponse.getSendDate());
    }
}
