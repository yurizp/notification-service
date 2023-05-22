package br.com.vibbra.notificationservice.controller.request.notification;

import br.com.vibbra.notificationservice.controller.request.notification.webpush.WebPushRequestStub;

public class NotificationRequestStub {
    public static NotificationRequest createWebpushRequest() {
        return build(WebPushRequestStub.create());
    }

    private static NotificationRequest build(SettingsRequest settingsRequest) {
        return NotificationRequest.builder().settings(settingsRequest).build();
    }
}