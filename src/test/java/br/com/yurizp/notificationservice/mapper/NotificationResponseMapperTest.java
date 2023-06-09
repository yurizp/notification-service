package br.com.yurizp.notificationservice.mapper;

import static org.junit.jupiter.api.Assertions.*;

import br.com.yurizp.notificationservice.controller.response.notificationsettings.NotificationResponse;
import br.com.yurizp.notificationservice.controller.response.notificationsettings.email.EmailResponse;
import br.com.yurizp.notificationservice.controller.response.notificationsettings.sms.SmsResponse;
import br.com.yurizp.notificationservice.controller.response.notificationsettings.webpush.WebpushResponse;
import br.com.yurizp.notificationservice.db.entity.notification.email.EmailEntity;
import br.com.yurizp.notificationservice.db.entity.notification.email.EmailEntityStub;
import br.com.yurizp.notificationservice.db.entity.notification.sms.SmsEntity;
import br.com.yurizp.notificationservice.db.entity.notification.sms.SmsEntityStub;
import br.com.yurizp.notificationservice.db.entity.notification.webpush.WebpushEntity;
import br.com.yurizp.notificationservice.db.entity.notification.webpush.WebpushEntityStub;
import org.junit.jupiter.api.Test;

class NotificationResponseMapperTest {

    @Test
    public void shouldReturnNotificationResponseToWebpush() {
        WebpushEntity webpushEntity = WebpushEntityStub.create();
        NotificationResponse result = NotificationResponseMapper.create(webpushEntity);
        assertNotNull(result.getSettings());
        assertTrue((result.getSettings() instanceof WebpushResponse));
    }

    @Test
    public void shouldReturnNotificationResponseToEmail() {
        EmailEntity email = EmailEntityStub.create();
        NotificationResponse result = NotificationResponseMapper.create(email);
        assertNotNull(result.getSettings());
        assertTrue((result.getSettings() instanceof EmailResponse));
    }

    @Test
    public void shouldReturnNotificationResponseToSms() {
        SmsEntity sms = SmsEntityStub.create();
        NotificationResponse result = NotificationResponseMapper.create(sms);
        assertNotNull(result.getSettings());
        assertTrue((result.getSettings() instanceof SmsResponse));
    }
}
