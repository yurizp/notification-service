package br.com.yurizp.notificationservice.db.entity.notification.webpush;

public class WebpushEntityStub {

    public static WebpushEntity create() {
        return WebpushEntity.builder()
                .id(85L)
                .site(siteCreate())
                .allowNotification(allowsCreate())
                .welcomeNotification(welcomeCreate())
                .build();
    }

    private static WelcomeNotificationEntity welcomeCreate() {
        return WelcomeNotificationEntity.builder()
                .id(93L)
                .messageText("Seja bem vindo!")
                .messageTitle("Vibbra")
                .urlRedirect("https://vibbra.com.br")
                .enableUrlRedirect(true)
                .build();
    }

    private static AllowNotificationEntity allowsCreate() {
        return AllowNotificationEntity.builder()
                .id(31L)
                .messageText("Você deseja receber notificações?")
                .allowButtonText("Permitir")
                .denyButtonText("Negar")
                .build();
    }

    private static SiteEntity siteCreate() {
        return SiteEntity.builder()
                .id(44L)
                .name("Vibbra")
                .address("https://vibbra.com.br")
                .urlIcon("https://vibbra.com.br/icon.png")
                .build();
    }
}
