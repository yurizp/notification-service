package br.com.yurizp.notificationservice.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.yurizp.notificationservice.controller.response.notificationsettings.sms.SmsResponse;
import br.com.yurizp.notificationservice.db.entity.notification.sms.SmsEntity;
import br.com.yurizp.notificationservice.db.entity.notification.sms.SmsEntityStub;
import br.com.yurizp.notificationservice.enums.Channel;
import org.junit.jupiter.api.Test;

class SmsResponseMapperTest {

    @Test
    public void shouldCreateSmsResponseMapper() {
        SmsEntity smsEntity = SmsEntityStub.create();

        SmsResponse smsResponse = SmsResponseMapper.create(smsEntity);

        assertAll(
                () -> assertEquals(Channel.SMS, smsResponse.getChannel()),
                () -> assertEquals(
                        smsEntity.getSite().getName(), smsResponse.getSite().getName()),
                () -> assertEquals(
                        smsEntity.getSite().getName(), smsResponse.getSite().getName()),
                () -> assertEquals(
                        smsEntity.getSite().getLogin(), smsResponse.getSite().getLogin()),
                () -> assertEquals(
                        smsEntity.getSite().getPassword(), smsResponse.getSite().getPassword()));
    }
}
