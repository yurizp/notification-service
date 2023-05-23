package br.com.vibbra.notificationservice.db.entity.notification.sms;

import net.bytebuddy.utility.RandomString;

import java.util.Random;

public class SmsEntityStub {

    public static SmsEntity create() {
            return SmsEntity.builder()
                    .site(SmsProviderEntity.builder()
                            .id(new Random().nextLong())
                            .login("login"+ new RandomString().nextString())
                            .name("name"+ new RandomString().nextString())
                            .password("password"+ new RandomString().nextString())
                            .build())
                    .build();
        }
}