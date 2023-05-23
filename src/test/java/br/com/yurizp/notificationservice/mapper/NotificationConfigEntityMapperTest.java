package br.com.yurizp.notificationservice.mapper;

import static org.junit.jupiter.api.Assertions.*;

import br.com.yurizp.notificationservice.db.entity.AppEntity;
import br.com.yurizp.notificationservice.db.entity.AppEntityStub;
import br.com.yurizp.notificationservice.enums.Channel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NotificationConfigEntityMapperTest {

    @Test
    public void shouldCreateNotificationConfigEntity() {
        AppEntity appEntity = AppEntityStub.create();
        var notificationConfigEntity = NotificationConfigEntityMapper.create(appEntity, Channel.WEBPUSHES);

        assertAll(
                () -> Assertions.assertEquals(appEntity, notificationConfigEntity.getApp()),
                () -> Assertions.assertEquals(Channel.WEBPUSHES, notificationConfigEntity.getChannel()),
                () -> Assertions.assertTrue(notificationConfigEntity.isEnabled()));
    }
}
