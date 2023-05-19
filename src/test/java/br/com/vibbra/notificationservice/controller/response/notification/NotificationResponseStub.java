package br.com.vibbra.notificationservice.controller.response.notification;

import br.com.vibbra.notificationservice.controller.response.notification.webpush.WebpushResponseStub;

public class NotificationResponseStub {

    public static NotificationResponse createWebpushResponse() {
        return build(WebpushResponseStub.create());
    }

    private static NotificationResponse build(SettingsResponse settingsResponse) {
        return NotificationResponse.builder().settings(settingsResponse).build();
    }
}
