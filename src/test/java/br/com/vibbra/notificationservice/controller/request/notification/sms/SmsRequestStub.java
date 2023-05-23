package br.com.vibbra.notificationservice.controller.request.notification.sms;

import br.com.vibbra.notificationservice.controller.request.notification.SettingsRequest;
import br.com.vibbra.notificationservice.enums.Channel;
import net.bytebuddy.utility.RandomString;

import static org.junit.jupiter.api.Assertions.*;

public class SmsRequestStub {

    public static SettingsRequest create() {
        return SmsRequest.builder()
                .channel(Channel.SMS)
                .site(SmsProvider.builder()
                        .login("login"+ new RandomString().nextString())
                        .name("name"+ new RandomString().nextString())
                        .password("password"+ new RandomString().nextString())
                        .build())
                .build();
    }
}