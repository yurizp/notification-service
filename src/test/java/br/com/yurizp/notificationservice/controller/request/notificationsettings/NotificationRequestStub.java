package br.com.yurizp.notificationservice.controller.request.notificationsettings;

import br.com.yurizp.notificationservice.controller.request.notificationsettings.email.EmailRequestStub;
import br.com.yurizp.notificationservice.controller.request.notificationsettings.sms.SmsRequestStub;
import br.com.yurizp.notificationservice.controller.request.notificationsettings.webpush.WebPushRequestStub;

public class NotificationRequestStub {
    public static NotificationRequest createWebpushRequest() {
        return build(WebPushRequestStub.create());
    }

    public static NotificationRequest createEmailRequest() {
        return build(EmailRequestStub.create());
    }

    private static NotificationRequest build(SettingsRequest settingsRequest) {
        return NotificationRequest.builder().settings(settingsRequest).build();
    }

    public static NotificationRequest createSmsRequest() {
        return build(SmsRequestStub.create());
    }
}
