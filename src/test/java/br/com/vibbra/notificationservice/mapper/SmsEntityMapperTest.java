package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.controller.request.notification.NotificationRequest;
import br.com.vibbra.notificationservice.controller.request.notification.NotificationRequestStub;
import br.com.vibbra.notificationservice.controller.request.notification.sms.SmsRequest;
import br.com.vibbra.notificationservice.db.entity.AppEntity;
import br.com.vibbra.notificationservice.db.entity.AppEntityStub;
import br.com.vibbra.notificationservice.db.entity.notification.sms.SmsEntity;
import br.com.vibbra.notificationservice.db.entity.notification.sms.SmsEntityStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmsEntityMapperTest {

    @Test
    public void shouldCreateSmsEntity() {

        AppEntity app = AppEntityStub.create();
        NotificationRequest smsRequest = NotificationRequestStub.createSmsRequest();
        SmsRequest smsSettings = (SmsRequest) smsRequest.getSettings();

        SmsEntity smsEntity = SmsEntityMapper.create(smsRequest, app);

        assertAll(
                () -> assertEquals(app, smsEntity.getApp()),
                () -> assertEquals(smsSettings.getSite().getName(), smsEntity.getSite().getName()),
                () -> assertEquals(smsSettings.getSite().getLogin(), smsEntity.getSite().getLogin()),
                () -> assertEquals(smsSettings.getSite().getPassword(), smsEntity.getSite().getPassword())
        );
    }

    @Test
    public void shouldUpdateSmsEntity() {
        NotificationRequest smsRequest = NotificationRequestStub.createSmsRequest();
        SmsEntity entity = SmsEntityStub.create();
        SmsRequest smsSettings = (SmsRequest) smsRequest.getSettings();

        SmsEntity smsEntity = SmsEntityMapper.update(entity, smsRequest);

        assertAll(
                () -> assertEquals(entity.getApp(), smsEntity.getApp()),
                () -> assertEquals(entity.getId(), smsEntity.getId()),
                () -> assertEquals(smsSettings.getSite().getName(), smsEntity.getSite().getName()),
                () -> assertEquals(smsSettings.getSite().getName(), smsEntity.getSite().getName()),
                () -> assertEquals(smsSettings.getSite().getLogin(), smsEntity.getSite().getLogin()),
                () -> assertEquals(smsSettings.getSite().getPassword(), smsEntity.getSite().getPassword())
        );
    }
}