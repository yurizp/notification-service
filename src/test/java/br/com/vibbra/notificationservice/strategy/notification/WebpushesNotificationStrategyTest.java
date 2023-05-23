package br.com.vibbra.notificationservice.strategy.notification;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.vibbra.notificationservice.controller.request.notificationsettings.NotificationRequest;
import br.com.vibbra.notificationservice.controller.request.notificationsettings.NotificationRequestStub;
import br.com.vibbra.notificationservice.controller.request.sendnotification.webpush.WebpushRequest;
import br.com.vibbra.notificationservice.controller.response.notificationsettings.NotificationResponse;
import br.com.vibbra.notificationservice.controller.response.sendnotification.HistoryNotification;
import br.com.vibbra.notificationservice.controller.response.sendnotification.NotificationHistoryResponse;
import br.com.vibbra.notificationservice.db.AppRepository;
import br.com.vibbra.notificationservice.db.NotificationConfigRepository;
import br.com.vibbra.notificationservice.db.NotificationHistoryRepository;
import br.com.vibbra.notificationservice.db.WebpushRepository;
import br.com.vibbra.notificationservice.db.entity.AppEntity;
import br.com.vibbra.notificationservice.db.entity.AppEntityStub;
import br.com.vibbra.notificationservice.db.entity.NotificationConfigEntity;
import br.com.vibbra.notificationservice.db.entity.NotificationHistoryEntity;
import br.com.vibbra.notificationservice.db.entity.NotificationHistoryEntityStub;
import br.com.vibbra.notificationservice.db.entity.notification.webpush.WebpushEntity;
import br.com.vibbra.notificationservice.db.entity.notification.webpush.WebpushEntityStub;
import br.com.vibbra.notificationservice.dto.NotificationConfigResponse;
import br.com.vibbra.notificationservice.enums.Channel;
import br.com.vibbra.notificationservice.exceptions.AppNotFoundException;
import br.com.vibbra.notificationservice.exceptions.SettingsNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WebpushesNotificationStrategyTest {

    @Mock
    private AppRepository appRepository;

    @Mock
    private WebpushRepository webpushRepository;

    @Mock
    private NotificationConfigRepository configRepository;

    @Mock
    private NotificationHistoryRepository historyRepository;

    @InjectMocks
    private WebpushesNotificationStrategy webpushesNotificationStrategy;

    @Test
    public void shouldCreateWebpushInSaveOrUpdateSuccessfully() {
        AppEntity appEntity = AppEntityStub.create();
        WebpushEntity webpushEntity = WebpushEntityStub.create();
        NotificationRequest notificationRequest = NotificationRequestStub.createWebpushRequest();

        when(appRepository.findByIdAndUserId(any(), any())).thenReturn(Optional.of(appEntity));
        when(webpushRepository.findByAppId(any())).thenReturn(Optional.empty());
        when(webpushRepository.save(any())).thenReturn(webpushEntity);

        webpushesNotificationStrategy.saveOrUpdateSettings(1L, 1L, Channel.WEBPUSHES, notificationRequest);

        verify(webpushRepository).save(any());
    }

    @Test
    public void shouldUpdateWebpushInSaveOrUpdateSuccessfully() {
        AppEntity appEntity = AppEntityStub.create();
        WebpushEntity webpushEntity = WebpushEntityStub.create();
        NotificationRequest notificationRequest = NotificationRequestStub.createWebpushRequest();

        when(appRepository.findByIdAndUserId(any(), any())).thenReturn(Optional.of(appEntity));
        when(webpushRepository.findByAppId(any())).thenReturn(Optional.of(webpushEntity));
        when(webpushRepository.save(any())).thenReturn(webpushEntity);

        webpushesNotificationStrategy.saveOrUpdateSettings(1L, 1L, Channel.WEBPUSHES, notificationRequest);

        verify(webpushRepository).save(any());
    }

    @Test
    public void shouldThrowErrorWhenNoFoundAppByUserAndAppIdInSaveOrUpdateSuccessfully() {
        when(appRepository.findByIdAndUserId(any(), any())).thenReturn(Optional.empty());

        assertThrows(
                AppNotFoundException.class,
                () -> webpushesNotificationStrategy.saveOrUpdateSettings(
                        1L, 1L, Channel.WEBPUSHES, new NotificationRequest()));

        verify(appRepository).findByIdAndUserId(any(), any());
    }

    @Test
    public void shouldEnableOrDisableSuccessfullyWhenIsNewConfiguration() {
        NotificationConfigEntity configResponse = NotificationConfigEntity.builder()
                .enabled(true)
                .channel(Channel.WEBPUSHES)
                .build();

        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(true);
        when(webpushRepository.existsByAppId(any())).thenReturn(true);
        when(appRepository.findByIdAndUserId(any(), any())).thenReturn(Optional.of(AppEntityStub.create()));
        when(configRepository.findByAppIdAndChannel(any(), any())).thenReturn(Optional.empty());
        when(configRepository.save(any())).thenReturn(configResponse);

        NotificationConfigResponse notificationConfigResponse =
                webpushesNotificationStrategy.enableOrDisableNotification(1L, 1L, Channel.WEBPUSHES);

        assertAll(
                () -> assertTrue(notificationConfigResponse.isCurrentStatus()),
                () -> assertFalse(notificationConfigResponse.isPreviousStatus()));
    }

    @Test
    public void shouldThrowErrorInEnableOrDisableWhenNoExistsAppToUserId() {
        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(false);

        assertThrows(
                AppNotFoundException.class,
                () -> webpushesNotificationStrategy.enableOrDisableNotification(1L, 1L, Channel.WEBPUSHES));
    }

    @Test
    public void shouldThrowErrorInEnableOrDisableWhenNoSettingsToApp() {
        when(webpushRepository.existsByAppId(any())).thenReturn(false);
        when(appRepository.existsByIdAndUserId(anyLong(), anyLong())).thenReturn(true);

        assertThrows(
                SettingsNotFoundException.class,
                () -> webpushesNotificationStrategy.enableOrDisableNotification(1L, 1L, Channel.WEBPUSHES));
    }

    @Test
    public void shouldEnableOrDisableSuccessfullyWhenAlreadyConfigured() {
        NotificationConfigEntity configResponse = NotificationConfigEntity.builder()
                .enabled(false)
                .channel(Channel.WEBPUSHES)
                .build();

        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(true);
        when(webpushRepository.existsByAppId(any())).thenReturn(true);
        when(configRepository.findByAppIdAndChannel(any(), any())).thenReturn(Optional.of(configResponse));
        when(configRepository.save(any())).thenReturn(configResponse);

        NotificationConfigResponse notificationConfigResponse =
                webpushesNotificationStrategy.enableOrDisableNotification(1L, 1L, Channel.WEBPUSHES);

        assertAll(
                () -> assertTrue(notificationConfigResponse.isCurrentStatus()),
                () -> assertFalse(notificationConfigResponse.isPreviousStatus()));
    }

    @Test
    public void shouldReturnConfigWhenExists() {
        WebpushEntity webpushEntity = WebpushEntityStub.create();

        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(true);
        when(webpushRepository.existsByAppId(any())).thenReturn(true);
        when(webpushRepository.findByAppId(any())).thenReturn(Optional.of(webpushEntity));

        NotificationResponse response = webpushesNotificationStrategy.findConfig(1L, 2L, Channel.WEBPUSHES);

        assertAll(() -> assertNotNull(response.getSettings()));
    }

    @Test
    public void shouldSendNotification() {
        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(true);
        when(webpushRepository.existsByAppId(any())).thenReturn(true);
        when(appRepository.findByIdAndUserId(any(), any())).thenReturn(Optional.of(AppEntityStub.create()));

        webpushesNotificationStrategy.sendNotification(1L, 1L, Channel.WEBPUSHES, new WebpushRequest());

        verify(historyRepository).save(any());
    }

    @Test
    public void shouldReturnNotifications() {
        List<NotificationHistoryEntity> historyEntities = NotificationHistoryEntityStub.createList();
        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(true);
        when(webpushRepository.existsByAppId(any())).thenReturn(true);
        when(historyRepository.findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqualAndAppIdAndChannel(
                        any(), any(), any(), any()))
                .thenReturn(historyEntities);

        List<NotificationHistoryResponse> historyExpected = historyEntities.stream()
                .map(n -> NotificationHistoryResponse.builder()
                        .sendDate(n.getCreatedAt())
                        .notificationId(n.getId())
                        .build())
                .collect(Collectors.toList());

        HistoryNotification notifications = webpushesNotificationStrategy.getNotifications(
                1L, 1L, Channel.WEBPUSHES, LocalDate.now().minusDays(4), LocalDate.now());

        assertThat(notifications.getHistory()).usingRecursiveComparison().isEqualTo(historyExpected);
    }
}
