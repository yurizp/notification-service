package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.controller.response.notification.sms.SmsResponse;
import br.com.vibbra.notificationservice.db.entity.notification.sms.SmsEntity;
import br.com.vibbra.notificationservice.db.entity.notification.sms.SmsEntityStub;
import br.com.vibbra.notificationservice.enums.Channel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SmsResponseMapperTest {

    @Test
    public void shouldCreateSmsResponseMapper() {
        SmsEntity smsEntity = SmsEntityStub.create();

        SmsResponse smsResponse = SmsResponseMapper.create(smsEntity);

        assertAll(
                () -> assertEquals(Channel.SMS, smsResponse.getChannel()),
                () -> assertEquals(smsEntity.getSite().getName(), smsResponse.getSite().getName()),
                () -> assertEquals(smsEntity.getSite().getName(), smsResponse.getSite().getName()),
                () -> assertEquals(smsEntity.getSite().getLogin(), smsResponse.getSite().getLogin()),
                () -> assertEquals(smsEntity.getSite().getPassword(), smsResponse.getSite().getPassword())
        );
    }
}