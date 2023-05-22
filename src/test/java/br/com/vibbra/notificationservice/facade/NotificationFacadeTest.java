package br.com.vibbra.notificationservice.facade;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.vibbra.notificationservice.controller.request.notification.NotificationRequest;
import br.com.vibbra.notificationservice.controller.response.notification.NotificationResponse;
import br.com.vibbra.notificationservice.dto.NotificationConfigResponse;
import br.com.vibbra.notificationservice.enums.Channel;
import br.com.vibbra.notificationservice.exceptions.ChannelNotImplementedFoundException;
import br.com.vibbra.notificationservice.strategy.NotificationStrategy;
import br.com.vibbra.notificationservice.strategy.ValidationNotificationStrategy;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NotificationFacadeTest {

    @Mock
    private NotificationStrategy webpushStrategy;

    @Mock
    private NotificationStrategy smsStrategy;

    @Mock
    private ValidationNotificationStrategy webpushValidationStrategy;

    @Mock
    private ValidationNotificationStrategy smsValidationStrategy;

    private NotificationFacade facade;

    @BeforeEach
    void setUp() {
        HashMap<Channel, NotificationStrategy> notifications = new HashMap<>();
        notifications.put(Channel.WEBPUSHES, webpushStrategy);
        notifications.put(Channel.SMS, smsStrategy);

        HashMap<Channel, ValidationNotificationStrategy> validations = new HashMap<>();
        validations.put(Channel.WEBPUSHES, webpushValidationStrategy);
        validations.put(Channel.SMS, smsValidationStrategy);

        facade = new NotificationFacade(notifications, validations);
    }

    @Test
    public void shouldSaveOrUpdateSettingsWhenSucessfuly() {
        NotificationRequest notification = new NotificationRequest();

        facade.saveOrUpdateSettings(1L, 1L, Channel.WEBPUSHES, notification);

        verify(webpushStrategy).saveOrUpdateSettings(1L, 1L, Channel.WEBPUSHES, notification);
        verify(webpushValidationStrategy).validate(1L, 1L, Channel.WEBPUSHES, notification);
    }

    @Test
    public void shouldReturnErrorWhenNotFoundStrategyToNotificationWhenSaveOrUpdateMethodIsCall() {
        assertThrows(
                ChannelNotImplementedFoundException.class,
                () -> facade.saveOrUpdateSettings(1L, 1L, Channel.EMAIL, null));
    }

    @Test
    public void shouldEnableOrDisableNotificationSucessfuly() {

        NotificationConfigResponse notifaction = new NotificationConfigResponse();
        when(webpushStrategy.enableOrDisableNotification(1L, 1L, Channel.WEBPUSHES))
                .thenReturn(notifaction);

        NotificationConfigResponse notificationConfigResponse =
                facade.enableOrDisableNotification(1L, 1L, Channel.WEBPUSHES);

        verify(webpushStrategy).enableOrDisableNotification(1L, 1L, Channel.WEBPUSHES);
        assertEquals(notificationConfigResponse, notifaction);
    }

    @Test
    public void shouldFindConfigSucessfuly() {

        NotificationResponse notifaction = new NotificationResponse();
        when(webpushStrategy.findConfig(1L, 1L, Channel.WEBPUSHES)).thenReturn(notifaction);

        NotificationResponse response = facade.findConfig(1L, 1L, Channel.WEBPUSHES);

        verify(webpushStrategy).findConfig(1L, 1L, Channel.WEBPUSHES);
        assertEquals(response, notifaction);
    }

    @Test
    public void shouldReturnErrorWhenNotFoundStrategyToNotificationWhenFindConfigIsCall() {
        assertThrows(
                ChannelNotImplementedFoundException.class,
                () -> facade.saveOrUpdateSettings(1L, 1L, Channel.EMAIL, null));
    }
}
