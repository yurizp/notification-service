package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.controller.response.notification.sms.SmsProvider;
import br.com.vibbra.notificationservice.controller.response.notification.sms.SmsResponse;
import br.com.vibbra.notificationservice.db.entity.notification.sms.SmsEntity;
import br.com.vibbra.notificationservice.db.entity.notification.sms.SmsProviderEntity;
import br.com.vibbra.notificationservice.enums.Channel;

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
