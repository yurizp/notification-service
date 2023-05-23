package br.com.vibbra.notificationservice.mapper;

import static org.junit.jupiter.api.Assertions.*;

import br.com.vibbra.notificationservice.db.entity.AppEntityStub;
import br.com.vibbra.notificationservice.enums.Channel;
import org.junit.jupiter.api.Test;

class NotificationHistoryEntityMapperTest {

    @Test
    public void shouldCreateNotificationHistoryEntity() {
        var appEntity = AppEntityStub.create();
        var channel = Channel.EMAIL.EMAIL;

        var notificationHistoryEntity = NotificationHistoryEntityMapper.create(appEntity, channel);

        assertNotNull(notificationHistoryEntity);
        assertEquals(appEntity, notificationHistoryEntity.getApp());
        assertEquals(channel, notificationHistoryEntity.getChannel());
        assertNotNull(notificationHistoryEntity.getCreatedAt());
    }
}
