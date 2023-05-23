package br.com.vibbra.notificationservice.controller.response.notificationsettings;

import br.com.vibbra.notificationservice.controller.response.notificationsettings.webpush.WebpushResponseStub;

public class NotificationResponseStub {

    public static NotificationResponse createWebpushResponse() {
        return build(WebpushResponseStub.create());
    }

    private static NotificationResponse build(SettingsResponse settingsResponse) {
        return NotificationResponse.builder().settings(settingsResponse).build();
    }
}
