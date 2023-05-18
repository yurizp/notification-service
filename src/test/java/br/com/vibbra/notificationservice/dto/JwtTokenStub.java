package br.com.vibbra.notificationservice.dto;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtTokenStub {

    public static JwtToken create() {
        return JwtToken.builder()
                .token("TOKEN")
                .expiration(Date.from(Instant.now().plus(100, ChronoUnit.DAYS)))
                .issuedAt(new Date())
                .user(createUser())
                .build();
    }

    private static UserToken createUser() {
        return UserToken.builder().email("valid@email.com").id(123L).build();
    }
}
