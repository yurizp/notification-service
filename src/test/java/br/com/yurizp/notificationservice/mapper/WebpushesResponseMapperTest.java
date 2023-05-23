package br.com.yurizp.notificationservice.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.yurizp.notificationservice.controller.response.notificationsettings.webpush.WebpushResponse;
import br.com.yurizp.notificationservice.db.entity.notification.webpush.WebpushEntity;
import br.com.yurizp.notificationservice.db.entity.notification.webpush.WebpushEntityStub;
import org.junit.jupiter.api.Test;

class WebpushesResponseMapperTest {

    @Test
    public void shouldCreateWebpushesResponse() {
        WebpushEntity webpushEntity = WebpushEntityStub.create();
        WebpushResponse webpushResponse = WebpushesResponseMapper.create(webpushEntity);

        assertAll(
                () -> assertEquals(
                        webpushEntity.getSite().getAddress(),
                        webpushResponse.getSite().getAddress()),
                () -> assertEquals(
                        webpushEntity.getSite().getName(),
                        webpushResponse.getSite().getName()),
                () -> assertEquals(
                        webpushEntity.getSite().getUrlIcon(),
                        webpushResponse.getSite().getUrlIcon()),
                () -> assertEquals(
                        webpushEntity.getWelcomeNotification().getMessageText(),
                        webpushResponse.getWelcomeNotification().getMessageText()),
                () -> assertEquals(
                        webpushEntity.getWelcomeNotification().getMessageTitle(),
                        webpushResponse.getWelcomeNotification().getMessageTitle()),
                () -> assertEquals(
                        webpushEntity.getWelcomeNotification().getUrlRedirect(),
                        webpushResponse.getWelcomeNotification().getUrlRedirect()),
                () -> assertEquals(
                        webpushEntity.getAllowNotification().getAllowButtonText(),
                        webpushResponse.getAllowNotification().getAllowButtonText()),
                () -> assertEquals(
                        webpushEntity.getAllowNotification().getDenyButtonText(),
                        webpushResponse.getAllowNotification().getDenyButtonText()),
                () -> assertEquals(
                        webpushEntity.getAllowNotification().getMessageText(),
                        webpushResponse.getAllowNotification().getMessageText()));
    }
}
