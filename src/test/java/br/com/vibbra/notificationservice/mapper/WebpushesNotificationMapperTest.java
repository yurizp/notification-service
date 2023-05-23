package br.com.vibbra.notificationservice.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import br.com.vibbra.notificationservice.controller.request.notification.NotificationRequest;
import br.com.vibbra.notificationservice.controller.request.notification.NotificationRequestStub;
import br.com.vibbra.notificationservice.controller.request.notification.webpush.WebPushRequest;
import br.com.vibbra.notificationservice.db.entity.AppEntity;
import br.com.vibbra.notificationservice.db.entity.AppEntityStub;
import br.com.vibbra.notificationservice.db.entity.notification.webpush.WebpushEntity;
import br.com.vibbra.notificationservice.db.entity.notification.webpush.WebpushEntityStub;
import org.junit.jupiter.api.Test;

class WebpushesNotificationMapperTest {

    @Test
    public void shouldCreateWebpushesEntity() {
        NotificationRequest notificationRequest = NotificationRequestStub.createWebpushRequest();
        WebPushRequest settings = (WebPushRequest) notificationRequest.getSettings();
        AppEntity appEntity = AppEntityStub.create();

        WebpushEntity entity = WebpushesEntityMapper.create(notificationRequest, appEntity);

        assertAll(
                () -> assertEquals(
                        settings.getSite().getAddress(), entity.getSite().getAddress()),
                () -> assertEquals(
                        settings.getSite().getName(), entity.getSite().getName()),
                () -> assertEquals(
                        settings.getSite().getUrlIcon(), entity.getSite().getUrlIcon()),
                () -> assertEquals(
                        settings.getSite().getUrlIcon(), entity.getSite().getUrlIcon()),
                () -> assertNull(entity.getSite().getId()),
                () -> assertEquals(
                        settings.getWelcomeNotification().getMessageText(),
                        entity.getWelcomeNotification().getMessageText()),
                () -> assertEquals(
                        settings.getWelcomeNotification().getMessageTitle(),
                        entity.getWelcomeNotification().getMessageTitle()),
                () -> assertEquals(
                        settings.getWelcomeNotification().getUrlRedirect(),
                        entity.getWelcomeNotification().getUrlRedirect()),
                () -> assertNull(entity.getWelcomeNotification().getId()),
                () -> assertEquals(
                        settings.getAllowNotification().getAllowButtonText(),
                        entity.getAllowNotification().getAllowButtonText()),
                () -> assertEquals(
                        settings.getAllowNotification().getDenyButtonText(),
                        entity.getAllowNotification().getDenyButtonText()),
                () -> assertEquals(
                        settings.getAllowNotification().getMessageText(),
                        entity.getAllowNotification().getMessageText()),
                () -> assertNull(entity.getAllowNotification().getId()));
    }

    @Test
    public void shouldUpdateWebpusheEntityWhenHaveEntity() {
        NotificationRequest notificationRequest = NotificationRequestStub.createWebpushRequest();
        WebpushEntity entityInput = WebpushEntityStub.create();

        WebpushEntity webpushEntity = WebpushesEntityMapper.update(entityInput, notificationRequest);

        assertAll(
                () -> assertEquals(
                        entityInput.getSite().getAddress(),
                        webpushEntity.getSite().getAddress()),
                () -> assertEquals(
                        entityInput.getSite().getName(), webpushEntity.getSite().getName()),
                () -> assertEquals(
                        entityInput.getSite().getUrlIcon(),
                        webpushEntity.getSite().getUrlIcon()),
                () -> assertEquals(
                        entityInput.getSite().getUrlIcon(),
                        webpushEntity.getSite().getUrlIcon()),
                () -> assertEquals(
                        entityInput.getSite().getId(), webpushEntity.getSite().getId()),
                () -> assertEquals(
                        entityInput.getWelcomeNotification().getMessageText(),
                        webpushEntity.getWelcomeNotification().getMessageText()),
                () -> assertEquals(
                        entityInput.getWelcomeNotification().getMessageTitle(),
                        webpushEntity.getWelcomeNotification().getMessageTitle()),
                () -> assertEquals(
                        entityInput.getWelcomeNotification().getUrlRedirect(),
                        webpushEntity.getWelcomeNotification().getUrlRedirect()),
                () -> assertEquals(
                        entityInput.getWelcomeNotification().getId(),
                        webpushEntity.getWelcomeNotification().getId()),
                () -> assertEquals(
                        entityInput.getAllowNotification().getAllowButtonText(),
                        webpushEntity.getAllowNotification().getAllowButtonText()),
                () -> assertEquals(
                        entityInput.getAllowNotification().getDenyButtonText(),
                        webpushEntity.getAllowNotification().getDenyButtonText()),
                () -> assertEquals(
                        entityInput.getAllowNotification().getMessageText(),
                        webpushEntity.getAllowNotification().getMessageText()),
                () -> assertEquals(
                        entityInput.getAllowNotification().getId(),
                        webpushEntity.getAllowNotification().getId()));
    }

    @Test
    public void shouldUpdateWebpusheEntityWhenExistsEntity() {
        NotificationRequest notificationRequest = NotificationRequestStub.createWebpushRequest();
        WebPushRequest settings = (WebPushRequest) notificationRequest.getSettings();
        WebpushEntity entityInput = WebpushEntityStub.create();

        WebpushEntity webpushEntity = WebpushesEntityMapper.update(entityInput, notificationRequest);

        assertAll(
                () -> assertEquals(
                        settings.getSite().getAddress(), webpushEntity.getSite().getAddress()),
                () -> assertEquals(
                        settings.getSite().getName(), webpushEntity.getSite().getName()),
                () -> assertEquals(
                        settings.getSite().getUrlIcon(), webpushEntity.getSite().getUrlIcon()),
                () -> assertEquals(
                        settings.getSite().getUrlIcon(), webpushEntity.getSite().getUrlIcon()),
                () -> assertEquals(
                        entityInput.getSite().getId(), webpushEntity.getSite().getId()),
                () -> assertEquals(
                        settings.getWelcomeNotification().getMessageText(),
                        webpushEntity.getWelcomeNotification().getMessageText()),
                () -> assertEquals(
                        settings.getWelcomeNotification().getMessageTitle(),
                        webpushEntity.getWelcomeNotification().getMessageTitle()),
                () -> assertEquals(
                        settings.getWelcomeNotification().getUrlRedirect(),
                        webpushEntity.getWelcomeNotification().getUrlRedirect()),
                () -> assertEquals(
                        entityInput.getWelcomeNotification().getId(),
                        webpushEntity.getWelcomeNotification().getId()),
                () -> assertEquals(
                        settings.getAllowNotification().getAllowButtonText(),
                        webpushEntity.getAllowNotification().getAllowButtonText()),
                () -> assertEquals(
                        settings.getAllowNotification().getDenyButtonText(),
                        webpushEntity.getAllowNotification().getDenyButtonText()),
                () -> assertEquals(
                        settings.getAllowNotification().getMessageText(),
                        webpushEntity.getAllowNotification().getMessageText()),
                () -> assertEquals(
                        entityInput.getAllowNotification().getId(),
                        webpushEntity.getAllowNotification().getId()));
    }

    @Test
    public void shouldUpdateWebpusheEntityWhenNotExistsEntityToSiteWelcomeAndAllowNotification() {
        NotificationRequest notificationRequest = NotificationRequestStub.createWebpushRequest();
        WebPushRequest settings = (WebPushRequest) notificationRequest.getSettings();

        WebpushEntity entityInput = WebpushEntityStub.create();
        entityInput.setSite(null);
        entityInput.setAllowNotification(null);
        entityInput.setWelcomeNotification(null);

        WebpushEntity webpushEntity = WebpushesEntityMapper.update(entityInput, notificationRequest);

        assertAll(
                () -> assertEquals(
                        settings.getSite().getAddress(), webpushEntity.getSite().getAddress()),
                () -> assertEquals(
                        settings.getSite().getName(), webpushEntity.getSite().getName()),
                () -> assertEquals(
                        settings.getSite().getUrlIcon(), webpushEntity.getSite().getUrlIcon()),
                () -> assertEquals(
                        settings.getSite().getUrlIcon(), webpushEntity.getSite().getUrlIcon()),
                () -> assertNull(webpushEntity.getSite().getId()),
                () -> assertEquals(
                        settings.getWelcomeNotification().getMessageText(),
                        webpushEntity.getWelcomeNotification().getMessageText()),
                () -> assertEquals(
                        settings.getWelcomeNotification().getMessageTitle(),
                        webpushEntity.getWelcomeNotification().getMessageTitle()),
                () -> assertEquals(
                        settings.getWelcomeNotification().getUrlRedirect(),
                        webpushEntity.getWelcomeNotification().getUrlRedirect()),
                () -> assertNull(webpushEntity.getWelcomeNotification().getId()),
                () -> assertEquals(
                        settings.getAllowNotification().getAllowButtonText(),
                        webpushEntity.getAllowNotification().getAllowButtonText()),
                () -> assertEquals(
                        settings.getAllowNotification().getDenyButtonText(),
                        webpushEntity.getAllowNotification().getDenyButtonText()),
                () -> assertEquals(
                        settings.getAllowNotification().getMessageText(),
                        webpushEntity.getAllowNotification().getMessageText()),
                () -> assertNull(webpushEntity.getAllowNotification().getId()));
    }
}
