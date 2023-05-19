package br.com.vibbra.notificationservice.controller.request.notification.webpush;

import br.com.vibbra.notificationservice.enums.Channel;

public class WebPushRequestStub {

    public static WebPushRequest create() {
        return WebPushRequest.builder()
                .channel(Channel.WEBPUSHES)
                .site(siteCreate())
                .allowNotification(allowsCreate())
                .welcomeNotification(welcomeCreate())
                .build();
    }

    private static WelcomeNotification welcomeCreate() {
        return WelcomeNotification.builder()
                .messageText("Seja bem vindo!")
                .messageTitle("Vibbra")
                .urlRedirect("https://vibbra.com.br")
                .enableUrlRedirect(true)
                .build();
    }

    private static AllowNotification allowsCreate() {
        return AllowNotification.builder()
                .messageText("Você deseja receber notificações?")
                .allowButtonText("Permitir")
                .denyButtonText("Negar")
                .build();
    }

    private static Site siteCreate() {
        return Site.builder()
                .name("Vibbra")
                .address("https://vibbra.com.br")
                .urlIcon("https://vibbra.com.br/icon.png")
                .build();
    }
}
