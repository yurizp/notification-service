package br.com.vibbra.notificationservice.mapper;

import static org.junit.jupiter.api.Assertions.*;

import br.com.vibbra.notificationservice.controller.response.notification.NotificationResponse;
import br.com.vibbra.notificationservice.db.entity.notification.webpush.WebpushEntity;
import br.com.vibbra.notificationservice.db.entity.notification.webpush.WebpushEntityStub;
import org.junit.jupiter.api.Test;

class NotificationResponseMapperTest {

    @Test
    public void shouldReturnNotificationResponse() {
        WebpushEntity webpushEntity = WebpushEntityStub.create();
        NotificationResponse result = NotificationResponseMapper.create(webpushEntity);
        assertNotNull(result.getSettings());
    }
}
