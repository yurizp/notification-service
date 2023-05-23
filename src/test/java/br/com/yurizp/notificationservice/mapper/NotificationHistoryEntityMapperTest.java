package br.com.yurizp.notificationservice.mapper;

import static org.junit.jupiter.api.Assertions.*;

import br.com.yurizp.notificationservice.db.entity.AppEntityStub;
import br.com.yurizp.notificationservice.enums.Channel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NotificationHistoryEntityMapperTest {

    @Test
    public void shouldCreateNotificationHistoryEntity() {
        var appEntity = AppEntityStub.create();
        var channel = Channel.EMAIL.EMAIL;

        var notificationHistoryEntity = NotificationHistoryEntityMapper.create(appEntity, channel);

        assertNotNull(notificationHistoryEntity);
        Assertions.assertEquals(appEntity, notificationHistoryEntity.getApp());
        Assertions.assertEquals(channel, notificationHistoryEntity.getChannel());
        assertNotNull(notificationHistoryEntity.getCreatedAt());
    }
}
