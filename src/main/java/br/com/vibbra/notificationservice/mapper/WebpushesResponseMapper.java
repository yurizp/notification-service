package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.controller.response.notification.webpush.AllowNotification;
import br.com.vibbra.notificationservice.controller.response.notification.webpush.Site;
import br.com.vibbra.notificationservice.controller.response.notification.webpush.WebpushResponse;
import br.com.vibbra.notificationservice.controller.response.notification.webpush.WelcomeNotification;
import br.com.vibbra.notificationservice.db.entity.notification.webpush.AllowNotificationEntity;
import br.com.vibbra.notificationservice.db.entity.notification.webpush.SiteEntity;
import br.com.vibbra.notificationservice.db.entity.notification.webpush.WebpushEntity;
import br.com.vibbra.notificationservice.db.entity.notification.webpush.WelcomeNotificationEntity;
import br.com.vibbra.notificationservice.enums.Channel;

public class WebpushesResponseMapper {

    public static WebpushResponse create(WebpushEntity webpushEntity) {
        return WebpushResponse.builder()
                .channel(Channel.WEBPUSHES)
                .site(siteCreate(webpushEntity.getSite()))
                .allowNotification(allowNotificationCreate(webpushEntity.getAllowNotification()))
                .welcomeNotification(welcomeNotificationCreate(webpushEntity.getWelcomeNotification()))
                .build();
    }

    private static Site siteCreate(SiteEntity site) {
        return Site.builder()
                .address(site.getAddress())
                .urlIcon(site.getUrlIcon())
                .name(site.getName())
                .build();
    }

    private static WelcomeNotification welcomeNotificationCreate(WelcomeNotificationEntity welcomeNotification) {
        return WelcomeNotification.builder()
                .enableUrlRedirect(welcomeNotification.isEnableUrlRedirect())
                .messageText(welcomeNotification.getMessageText())
                .messageTitle(welcomeNotification.getMessageTitle())
                .urlRedirect(welcomeNotification.getUrlRedirect())
                .build();
    }

    private static AllowNotification allowNotificationCreate(AllowNotificationEntity allowNotification) {
        return AllowNotification.builder()
                .allowButtonText(allowNotification.getAllowButtonText())
                .messageText(allowNotification.getMessageText())
                .denyButtonText(allowNotification.getDenyButtonText())
                .build();
    }
}
