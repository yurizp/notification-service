package br.com.vibbra.notificationservice.strategy.notification;

import br.com.vibbra.notificationservice.controller.request.notification.NotificationRequest;
import br.com.vibbra.notificationservice.controller.request.notification.NotificationRequestStub;
import br.com.vibbra.notificationservice.controller.response.notification.NotificationResponse;
import br.com.vibbra.notificationservice.db.AppRepository;
import br.com.vibbra.notificationservice.db.EmailRepository;
import br.com.vibbra.notificationservice.db.NotificationConfigRepository;
import br.com.vibbra.notificationservice.db.entity.AppEntity;
import br.com.vibbra.notificationservice.db.entity.AppEntityStub;
import br.com.vibbra.notificationservice.db.entity.NotificationConfigEntity;
import br.com.vibbra.notificationservice.db.entity.notification.email.EmailEntity;
import br.com.vibbra.notificationservice.db.entity.notification.email.EmailEntityStub;
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
class EmailNotificationStrategyTest {

    @Mock
    private AppRepository appRepository;

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private NotificationConfigRepository configRepository;

    @InjectMocks
    private EmailNotificationStrategy emailNotificationStrategy;

    @Test
    public void shouldCreateInSaveOrUpdateSuccessfully() {
        AppEntity appEntity = AppEntityStub.create();
        EmailEntity emailEntity = EmailEntityStub.create();
        NotificationRequest notificationRequest = NotificationRequestStub.createEmailRequest();

        when(appRepository.findByIdAndUserId(any(), any())).thenReturn(Optional.of(appEntity));
        when(emailRepository.findByAppId(any())).thenReturn(Optional.empty());
        when(emailRepository.save(any())).thenReturn(emailEntity);

        emailNotificationStrategy.saveOrUpdateSettings(1L, 1L, Channel.EMAIL, notificationRequest);

        verify(emailRepository).save(any());
    }

    @Test
    public void shouldUpdateInSaveOrUpdateSuccessfully() {
        AppEntity appEntity = AppEntityStub.create();
        EmailEntity emailEntity = EmailEntityStub.create();
        NotificationRequest notificationRequest = NotificationRequestStub.createEmailRequest();

        when(appRepository.findByIdAndUserId(any(), any())).thenReturn(Optional.of(appEntity));
        when(emailRepository.findByAppId(any())).thenReturn(Optional.of(emailEntity));
        when(emailRepository.save(any())).thenReturn(emailEntity);

        emailNotificationStrategy.saveOrUpdateSettings(1L, 1L, Channel.EMAIL, notificationRequest);

        verify(emailRepository).save(any());
    }

    @Test
    public void shouldThrowErrorWhenNoFoundAppByUserAndAppIdInSaveOrUpdateSuccessfully() {
        when(appRepository.findByIdAndUserId(any(), any())).thenReturn(Optional.empty());

        assertThrows(
                AppNotFoundException.class,
                () -> emailNotificationStrategy.saveOrUpdateSettings(
                        1L, 1L, Channel.EMAIL, new NotificationRequest()));

        verify(appRepository).findByIdAndUserId(any(), any());
    }

    @Test
    public void shouldEnableOrDisableSuccessfullyWhenIsNewConfiguration() {
        NotificationConfigEntity configResponse = NotificationConfigEntity.builder()
                .enabled(true)
                .channel(Channel.EMAIL)
                .build();

        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(true);
        when(emailRepository.existsByAppId(any())).thenReturn(true);
        when(appRepository.findByIdAndUserId(any(), any())).thenReturn(Optional.of(AppEntityStub.create()));
        when(configRepository.findByAppIdAndChannel(any(), any())).thenReturn(Optional.empty());
        when(configRepository.save(any())).thenReturn(configResponse);

        NotificationConfigResponse notificationConfigResponse =
                emailNotificationStrategy.enableOrDisableNotification(1L, 1L, Channel.EMAIL);

        assertAll(
                () -> assertTrue(notificationConfigResponse.isCurrentStatus()),
                () -> assertFalse(notificationConfigResponse.isPreviousStatus()));
    }

    @Test
    public void shouldThrowErrorInEnableOrDisableWhenNoExistsAppToUserId() {
        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(false);

        assertThrows(
                AppNotFoundException.class,
                () -> emailNotificationStrategy.enableOrDisableNotification(1L, 1L, Channel.EMAIL));
    }

    @Test
    public void shouldThrowErrorInEnableOrDisableWhenNoSettingsToApp() {
        when(emailRepository.existsByAppId(any())).thenReturn(false);
        when(appRepository.existsByIdAndUserId(anyLong(), anyLong())).thenReturn(true);

        assertThrows(
                SettingsNotFoundException.class,
                () -> emailNotificationStrategy.enableOrDisableNotification(1L, 1L, Channel.EMAIL));
    }

    @Test
    public void shouldEnableOrDisableSuccessfullyWhenAlreadyConfigured() {
        NotificationConfigEntity configResponse = NotificationConfigEntity.builder()
                .enabled(false)
                .channel(Channel.EMAIL)
                .build();

        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(true);
        when(emailRepository.existsByAppId(any())).thenReturn(true);
        when(configRepository.findByAppIdAndChannel(any(), any())).thenReturn(Optional.of(configResponse));
        when(configRepository.save(any())).thenReturn(configResponse);

        NotificationConfigResponse notificationConfigResponse =
                emailNotificationStrategy.enableOrDisableNotification(1L, 1L, Channel.EMAIL);

        assertAll(
                () -> assertTrue(notificationConfigResponse.isCurrentStatus()),
                () -> assertFalse(notificationConfigResponse.isPreviousStatus()));
    }

    @Test
    public void shouldReturnConfigWhenExists() {
        EmailEntity email = EmailEntityStub.create();

        when(appRepository.existsByIdAndUserId(any(), any())).thenReturn(true);
        when(emailRepository.existsByAppId(any())).thenReturn(true);
        when(emailRepository.findByAppId(any())).thenReturn(Optional.of(email));

        NotificationResponse response = emailNotificationStrategy.findConfig(1L, 2L, Channel.EMAIL);

        assertAll(() -> assertNotNull(response.getSettings()));
    }

    @Test
    public void shouldThrowErrorWhenNoExistsAppToUserInGetConfig() {
        when(appRepository.existsByIdAndUserId(any(),any())).thenReturn(false);

        assertThrows(AppNotFoundException.class, ()-> emailNotificationStrategy.findConfig(1L, 2L, Channel.EMAIL));
    }

    @Test
    public void shouldThrowErrorWhenNoExistsSettingsInGetConfig() {
        when(appRepository.existsByIdAndUserId(any(),any())).thenReturn(true);
        when(emailRepository.existsByAppId(any())).thenReturn(false);

        assertThrows(SettingsNotFoundException.class, ()-> emailNotificationStrategy.findConfig(1L, 2L, Channel.EMAIL));
    }
}
