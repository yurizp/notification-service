package br.com.yurizp.notificationservice.controller.request.notificationsettings.sms;

import br.com.yurizp.notificationservice.controller.request.notificationsettings.SettingsRequest;
import br.com.yurizp.notificationservice.enums.Channel;
import net.bytebuddy.utility.RandomString;

public class SmsRequestStub {

    public static SettingsRequest create() {
        return SmsRequest.builder()
                .channel(Channel.SMS)
                .site(SmsProvider.builder()
                        .login("login" + new RandomString().nextString())
                        .name("name" + new RandomString().nextString())
                        .password("password" + new RandomString().nextString())
                        .build())
                .build();
    }
}
