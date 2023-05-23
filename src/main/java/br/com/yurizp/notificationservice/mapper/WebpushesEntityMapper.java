package br.com.yurizp.notificationservice.mapper;

import br.com.yurizp.notificationservice.controller.request.notificationsettings.NotificationRequest;
import br.com.yurizp.notificationservice.controller.request.notificationsettings.webpush.AllowNotification;
import br.com.yurizp.notificationservice.controller.request.notificationsettings.webpush.Site;
import br.com.yurizp.notificationservice.controller.request.notificationsettings.webpush.WebPushRequest;
import br.com.yurizp.notificationservice.controller.request.notificationsettings.webpush.WelcomeNotification;
import br.com.yurizp.notificationservice.db.entity.AppEntity;
import br.com.yurizp.notificationservice.db.entity.notification.webpush.AllowNotificationEntity;
import br.com.yurizp.notificationservice.db.entity.notification.webpush.SiteEntity;
import br.com.yurizp.notificationservice.db.entity.notification.webpush.WebpushEntity;
import br.com.yurizp.notificationservice.db.entity.notification.webpush.WelcomeNotificationEntity;
import br.com.yurizp.notificationservice.exceptions.InvalidBodyException;

import java.util.Objects;

public class WebpushesEntityMapper {

    public static WebpushEntity update(WebpushEntity pushEntity, NotificationRequest notification) {
        if (!(notification.getSettings() instanceof WebPushRequest)) {
            throw new InvalidBodyException();
        }
        WebPushRequest request = (WebPushRequest) notification.getSettings();

        Long allowNotificationId = Objects.isNull(pushEntity.getAllowNotification())
                ? null
                : pushEntity.getAllowNotification().getId();
        Long welcomeNotificationId = Objects.isNull(pushEntity.getWelcomeNotification())
                ? null
                : pushEntity.getWelcomeNotification().getId();
        Long siteId = Objects.isNull(pushEntity.getSite())
                ? null
                : pushEntity.getSite().getId();

        return WebpushEntity.builder()
                .id(pushEntity.getId())
                .app(pushEntity.getApp())
                .allowNotification(allowNotificationCreate(allowNotificationId, request.getAllowNotification()))
                .welcomeNotification(welcomeNotificationCreate(welcomeNotificationId, request.getWelcomeNotification()))
                .site(siteCreate(siteId, request.getSite()))
                .build();
    }

    public static WebpushEntity create(NotificationRequest notification, AppEntity app) {
        if (!(notification.getSettings() instanceof WebPushRequest)) {
            throw new InvalidBodyException();
        }
        WebPushRequest request = (WebPushRequest) notification.getSettings();
        return WebpushEntity.builder()
                .app(app)
                .allowNotification(allowNotificationCreate(null, request.getAllowNotification()))
                .welcomeNotification(welcomeNotificationCreate(null, request.getWelcomeNotification()))
                .site(siteCreate(null, request.getSite()))
                .build();
    }

    private static SiteEntity siteCreate(Long id, Site site) {
        return SiteEntity.builder()
                .id(id)
                .address(site.getAddress())
                .urlIcon(site.getUrlIcon())
                .name(site.getName())
                .build();
    }

    private static WelcomeNotificationEntity welcomeNotificationCreate(
            Long id, WelcomeNotification welcomeNotification) {
        return WelcomeNotificationEntity.builder()
                .id(id)
                .enableUrlRedirect(welcomeNotification.isEnableUrlRedirect())
                .messageText(welcomeNotification.getMessageText())
                .messageTitle(welcomeNotification.getMessageTitle())
                .urlRedirect(welcomeNotification.getUrlRedirect())
                .build();
    }

    private static AllowNotificationEntity allowNotificationCreate(Long id, AllowNotification allowNotification) {
        return AllowNotificationEntity.builder()
                .id(id)
                .allowButtonText(allowNotification.getAllowButtonText())
                .messageText(allowNotification.getMessageText())
                .denyButtonText(allowNotification.getDenyButtonText())
                .build();
    }
}
