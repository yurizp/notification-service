package br.com.vibbra.notificationservice.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.vibbra.notificationservice.dto.JwtToken;
import br.com.vibbra.notificationservice.dto.User;
import br.com.vibbra.notificationservice.dto.UserStub;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import java.time.Instant;
import java.util.Date;
import org.junit.jupiter.api.Test;

class JwtTokenMapperTest {

    @Test
    public void shouldCreateJwtTokenFromUserSuccessfully() {
        User user = UserStub.create();
        String token = "TOKEN";
        Date expiration = Date.from(Instant.now().plusSeconds(1000));
        Date issuedAt = new Date();

        JwtToken jwtToken = JwtTokenMapper.create(token, user, expiration, issuedAt);

        assertAll(
                () -> assertEquals(token, jwtToken.getToken()),
                () -> assertEquals(expiration, jwtToken.getExpiration()),
                () -> assertEquals(issuedAt, jwtToken.getIssuedAt()),
                () -> assertNotNull(jwtToken.getUser()),
                () -> assertEquals(user.getEmail(), jwtToken.getUser().getEmail()),
                () -> assertEquals(user.getId(), jwtToken.getUser().getId()));
    }

    @Test
    public void shouldCreateJwtTokenFromClaimsSuccessfully() {
        String token = "TOKEN";
        User user = UserStub.create();

        Claims claims = new DefaultClaims();
        claims.put("userEmail", user.getEmail());
        claims.put("userId", user.getId());
        claims.put("iat", 1684402669);
        claims.put("exp", 1684404469);
        claims.put("sub", user.getEmail());

        JwtToken jwtToken = JwtTokenMapper.create(claims, token);

        assertAll(
                () -> assertEquals(token, jwtToken.getToken()),
                () -> assertNotNull(jwtToken.getExpiration()),
                () -> assertNotNull(jwtToken.getIssuedAt()),
                () -> assertNotNull(jwtToken.getUser()),
                () -> assertEquals(user.getEmail(), jwtToken.getUser().getEmail()),
                () -> assertEquals(user.getId(), jwtToken.getUser().getId()));
    }
}
