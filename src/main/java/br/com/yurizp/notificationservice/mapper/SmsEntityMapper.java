package br.com.yurizp.notificationservice.mapper;

import br.com.yurizp.notificationservice.controller.request.notificationsettings.NotificationRequest;
import br.com.yurizp.notificationservice.controller.request.notificationsettings.sms.SmsProvider;
import br.com.yurizp.notificationservice.controller.request.notificationsettings.sms.SmsRequest;
import br.com.yurizp.notificationservice.db.entity.AppEntity;
import br.com.yurizp.notificationservice.db.entity.notification.sms.SmsEntity;
import br.com.yurizp.notificationservice.db.entity.notification.sms.SmsProviderEntity;
import br.com.yurizp.notificationservice.exceptions.InvalidBodyException;

public class SmsEntityMapper {

    public static SmsEntity create(NotificationRequest notification, AppEntity app) {
        if (!(notification.getSettings() instanceof SmsRequest)) {
            throw new InvalidBodyException();
        }
        SmsRequest smsRequest = (SmsRequest) notification.getSettings();
        return SmsEntity.builder()
                .app(app)
                .site(createSite(smsRequest.getSite(), null))
                .build();
    }

    private static SmsProviderEntity createSite(SmsProvider smsProvider, Long id) {
        return SmsProviderEntity.builder()
                .id(id)
                .login(smsProvider.getLogin())
                .name(smsProvider.getName())
                .password(smsProvider.getPassword())
                .build();
    }

    public static SmsEntity update(SmsEntity entity, NotificationRequest notification) {
        if (!(notification.getSettings() instanceof SmsRequest)) {
            throw new InvalidBodyException();
        }
        SmsRequest smsRequest = (SmsRequest) notification.getSettings();

        entity.setSite(createSite(smsRequest.getSite(), entity.getSite().getId()));
        return entity;
    }
}
