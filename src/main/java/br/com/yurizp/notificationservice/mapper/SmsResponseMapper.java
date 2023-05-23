package br.com.yurizp.notificationservice.mapper;

import br.com.yurizp.notificationservice.controller.response.notificationsettings.sms.SmsProvider;
import br.com.yurizp.notificationservice.controller.response.notificationsettings.sms.SmsResponse;
import br.com.yurizp.notificationservice.db.entity.notification.sms.SmsEntity;
import br.com.yurizp.notificationservice.db.entity.notification.sms.SmsProviderEntity;
import br.com.yurizp.notificationservice.enums.Channel;

public class SmsResponseMapper {

    public static SmsResponse create(SmsEntity smsEntity) {
        return SmsResponse.builder()
                .channel(Channel.SMS)
                .site(createSite(smsEntity.getSite()))
                .build();
    }

    private static SmsProvider createSite(SmsProviderEntity smsProvider) {
        return SmsProvider.builder()
                .login(smsProvider.getLogin())
                .name(smsProvider.getName())
                .password(smsProvider.getPassword())
                .build();
    }
}
