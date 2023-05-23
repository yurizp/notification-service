package br.com.yurizp.notificationservice.strategy.notification;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.yurizp.notificationservice.controller.request.notificationsettings.NotificationRequest;
import br.com.yurizp.notificationservice.controller.request.notificationsettings.NotificationRequestStub;
import br.com.yurizp.notificationservice.controller.request.sendnotification.sms.SmsRequest;
import br.com.yurizp.notificationservice.controller.response.notificationsettings.NotificationResponse;
import br.com.yurizp.notificationservice.controller.response.sendnotification.HistoryNotification;
import br.com.yurizp.notificationservice.controller.response.sendnotification.NotificationHistoryResponse;
import br.com.yurizp.notificationservice.db.AppRepository;
import br.com.yurizp.notificationservice.db.NotificationConfigRepository;
import br.com.yurizp.notificationservice.db.NotificationHistoryRepository;
import br.com.yurizp.notificationservice.db.SmsRepository;
import br.com.yurizp.notificationservice.db.entity.AppEntity;
import br.com.yurizp.notificationservice.db.entity.AppEntityStub;
import br.com.yurizp.notificationservice.db.entity.NotificationConfigEntity;
import br.com.yurizp.notificationservice.db.entity.NotificationHistoryEntity;
import br.com.yurizp.notificationservice.db.entity.NotificationHistoryEntityStub;
import br.com.yurizp.notificationservice.db.entity.notification.sms.SmsEntity;
import br.com.yurizp.notificationservice.db.entity.notification.sms.SmsEntityStub;
import br.com.yurizp.notificationservice.dto.NotificationConfigResponse;
import br.com.yurizp.notificationservice.enums.Channel;
import br.com.yurizp.notificationservice.exceptions.AppNotFoundException;
import br.com.yurizp.notificationservice.exceptions.SettingsNotFoundException;
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
class SmsNotificationStrategyTest {

    @Mock
    private AppRepository appRepository;

    @Mock
    private SmsRepository smsRepository;

    @Mock
    private NotificationConfigRepository configRepository;

    @Mock
    private NotificationHistoryRepository historyRepository;

    @InjectMocks
    private SmsNotificationStrategy smsNotificationStrategy;

    @Test
    public void shouldCreateInSaveOrUpdateSuccessfully() {
        AppEntity appEntity = AppEntityStub.create();
        SmsEntity sms = SmsEntityStub.create();
        NotificationRequest notificationRequest = NotificationRequestStub.createSmsRequest();

        when(appRepository.findByIdAndUserId(any(), any())).thenReturn(Optional.of(appEntity));
        when(smsRepository.findByAppId(any())).thenReturn(Optional.empty());
        when(smsRepository.save(any())).thenReturn(sms);

        smsNotificationStrategy.saveOrUpdateSettings(1L, 1L, Channel.SMS, notificationRequest);

        verify(smsRepository).save(any());
    }

    @Test
    public void shouldUpdateInSaveOrUpdateSuccessfully() {
        AppEntity appEntity = AppEntityStub.create();
        SmsEntity sms = SmsEntityStub.create();
        NotificationRequest notificationRequest = NotificationRequestStub.createSmsRequest();

        when(appRepository.findByIdAndUserId(any(), any())).thenReturn(Optional.of(appEntity));
        when(smsRepository.findByAppId(any())).thenReturn(Optional.of(sms));
        when(smsRepository.save(any())).thenReturn(sms);

        smsNotificationStrategy.saveOrUpdateSettings(1L, 1L, Channel.SMS, notificationRequest);

        verify(smsRepository).save(any());
    }

    @Test
    public void shouldThrowErrorWhenNoFoundAppByUserAndAppIdInSaveOrUpdateSuccessfully() {
        when(appRepository.findByIdAndUserId(any(), any())).thenReturn(Optional.empty());

        assertThrows(
                AppNotFoundException.class,
                () -> smsNotificationStrategy.saveOrUpdateSettings(1L, 1L, Channel.SMS, new NotificationRequest()));

        verify(appRepository).findByIdAndUserId(any(), any());
    }

    @Test
    public void shouldEnableOrDisableSuccessfullyWhenIsNewConfiguration() {
        NotificationConfigEntity configResponse = NotificationConfigEntity.builder()
                .enabled(true)
                .channel(Channel.SMS)
                .build();

        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(true);
        when(smsRepository.existsByAppId(any())).thenReturn(true);
        when(appRepository.findByIdAndUserId(any(), any())).thenReturn(Optional.of(AppEntityStub.create()));
        when(configRepository.findByAppIdAndChannel(any(), any())).thenReturn(Optional.empty());
        when(configRepository.save(any())).thenReturn(configResponse);

        NotificationConfigResponse notificationConfigResponse =
                smsNotificationStrategy.enableOrDisableNotification(1L, 1L, Channel.SMS);

        assertAll(
                () -> assertTrue(notificationConfigResponse.isCurrentStatus()),
                () -> assertFalse(notificationConfigResponse.isPreviousStatus()));
    }

    @Test
    public void shouldThrowErrorInEnableOrDisableWhenNoExistsAppToUserId() {
        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(false);

        assertThrows(
                AppNotFoundException.class,
                () -> smsNotificationStrategy.enableOrDisableNotification(1L, 1L, Channel.SMS));
    }

    @Test
    public void shouldThrowErrorInEnableOrDisableWhenNoSettingsToApp() {
        when(smsRepository.existsByAppId(any())).thenReturn(false);
        when(appRepository.existsByIdAndUserId(anyLong(), anyLong())).thenReturn(true);

        assertThrows(
                SettingsNotFoundException.class,
                () -> smsNotificationStrategy.enableOrDisableNotification(1L, 1L, Channel.SMS));
    }

    @Test
    public void shouldEnableOrDisableSuccessfullyWhenAlreadyConfigured() {
        NotificationConfigEntity configResponse = NotificationConfigEntity.builder()
                .enabled(false)
                .channel(Channel.SMS)
                .build();

        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(true);
        when(smsRepository.existsByAppId(any())).thenReturn(true);
        when(configRepository.findByAppIdAndChannel(any(), any())).thenReturn(Optional.of(configResponse));
        when(configRepository.save(any())).thenReturn(configResponse);

        NotificationConfigResponse notificationConfigResponse =
                smsNotificationStrategy.enableOrDisableNotification(1L, 1L, Channel.SMS);

        assertAll(
                () -> assertTrue(notificationConfigResponse.isCurrentStatus()),
                () -> assertFalse(notificationConfigResponse.isPreviousStatus()));
    }

    @Test
    public void shouldReturnConfigWhenExists() {
        SmsEntity sms = SmsEntityStub.create();

        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(true);
        when(smsRepository.existsByAppId(any())).thenReturn(true);
        when(smsRepository.findByAppId(any())).thenReturn(Optional.of(sms));

        NotificationResponse response = smsNotificationStrategy.findConfig(1L, 2L, Channel.SMS);

        assertAll(() -> assertNotNull(response.getSettings()));
    }

    @Test
    public void shouldThrowErrorWhenNoExistsAppToUserInGetConfig() {
        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(false);

        assertThrows(AppNotFoundException.class, () -> smsNotificationStrategy.findConfig(1L, 2L, Channel.SMS));
    }

    @Test
    public void shouldThrowErrorWhenNoExistsSettingsInGetConfig() {
        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(true);
        when(smsRepository.existsByAppId(any())).thenReturn(false);

        assertThrows(SettingsNotFoundException.class, () -> smsNotificationStrategy.findConfig(1L, 2L, Channel.SMS));
    }

    @Test
    public void shouldSendNotification() {
        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(true);
        when(smsRepository.existsByAppId(any())).thenReturn(true);
        when(appRepository.findByIdAndUserId(any(), any())).thenReturn(Optional.of(AppEntityStub.create()));

        smsNotificationStrategy.sendNotification(1L, 1L, Channel.SMS, new SmsRequest());

        verify(historyRepository).save(any());
    }

    @Test
    public void shouldReturnNotifications() {
        List<NotificationHistoryEntity> historyEntities = NotificationHistoryEntityStub.createList();
        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(true);
        when(smsRepository.existsByAppId(any())).thenReturn(true);
        when(historyRepository.findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqualAndAppIdAndChannel(
                        any(), any(), any(), any()))
                .thenReturn(historyEntities);

        List<NotificationHistoryResponse> historyExpected = historyEntities.stream()
                .map(n -> NotificationHistoryResponse.builder()
                        .sendDate(n.getCreatedAt())
                        .notificationId(n.getId())
                        .build())
                .collect(Collectors.toList());

        HistoryNotification notifications = smsNotificationStrategy.getNotifications(
                1L, 1L, Channel.SMS, LocalDate.now().minusDays(4), LocalDate.now());

        assertThat(notifications.getHistory()).usingRecursiveComparison().isEqualTo(historyExpected);
    }
}
