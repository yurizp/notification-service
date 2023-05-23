package br.com.vibbra.notificationservice.mapper;

import static org.junit.jupiter.api.Assertions.*;

import br.com.vibbra.notificationservice.db.entity.AppEntity;
import br.com.vibbra.notificationservice.db.entity.AppEntityStub;
import br.com.vibbra.notificationservice.enums.Channel;
import org.junit.jupiter.api.Test;

class NotificationConfigEntityMapperTest {

    @Test
    public void shouldCreateNotificationConfigEntity() {
        AppEntity appEntity = AppEntityStub.create();
        var notificationConfigEntity = NotificationConfigEntityMapper.create(appEntity, Channel.WEBPUSHES);

        assertAll(
                () -> assertEquals(appEntity, notificationConfigEntity.getApp()),
                () -> assertEquals(Channel.WEBPUSHES, notificationConfigEntity.getChannel()),
                () -> assertTrue(notificationConfigEntity.isEnabled()));
    }
}
