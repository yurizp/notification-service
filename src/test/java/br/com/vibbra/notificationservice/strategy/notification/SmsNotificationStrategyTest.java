package br.com.vibbra.notificationservice.strategy.notification;

import br.com.vibbra.notificationservice.controller.request.notification.NotificationRequest;
import br.com.vibbra.notificationservice.controller.request.notification.NotificationRequestStub;
import br.com.vibbra.notificationservice.controller.response.notification.NotificationResponse;
import br.com.vibbra.notificationservice.db.AppRepository;
import br.com.vibbra.notificationservice.db.NotificationConfigRepository;
import br.com.vibbra.notificationservice.db.SmsRepository;
import br.com.vibbra.notificationservice.db.entity.AppEntity;
import br.com.vibbra.notificationservice.db.entity.AppEntityStub;
import br.com.vibbra.notificationservice.db.entity.NotificationConfigEntity;
import br.com.vibbra.notificationservice.db.entity.notification.sms.SmsEntity;
import br.com.vibbra.notificationservice.db.entity.notification.sms.SmsEntityStub;
import br.com.vibbra.notificationservice.dto.NotificationConfigResponse;
import br.com.vibbra.notificationservice.enums.Channel;
import br.com.vibbra.notificationservice.exceptions.AppNotFoundException;
import br.com.vibbra.notificationservice.exceptions.SettingsNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SmsNotificationStrategyTest {

    @Mock
    private AppRepository appRepository;

    @Mock
    private SmsRepository smsRepository;
    @Mock
    private NotificationConfigRepository configRepository;

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
                () -> smsNotificationStrategy.saveOrUpdateSettings(
                        1L, 1L, Channel.SMS, new NotificationRequest()));

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
        when(appRepository.existsByIdAndUserId(any(),any())).thenReturn(false);

        assertThrows(AppNotFoundException.class, ()-> smsNotificationStrategy.findConfig(1L, 2L, Channel.SMS));
    }

    @Test
    public void shouldThrowErrorWhenNoExistsSettingsInGetConfig() {
        when(appRepository.existsByIdAndUserId(any(),any())).thenReturn(true);
        when(smsRepository.existsByAppId(any())).thenReturn(false);

        assertThrows(SettingsNotFoundException.class, ()-> smsNotificationStrategy.findConfig(1L, 2L, Channel.SMS));
    }
}
