package br.com.vibbra.notificationservice.controller.request.notification;

import br.com.vibbra.notificationservice.controller.request.notification.email.EmailRequestStub;
import br.com.vibbra.notificationservice.controller.request.notification.sms.SmsRequestStub;
import br.com.vibbra.notificationservice.controller.request.notification.webpush.WebPushRequestStub;

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
