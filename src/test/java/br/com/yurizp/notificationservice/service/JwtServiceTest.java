package br.com.yurizp.notificationservice.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.vibbra.notificationservice.dto.JwtToken;
import br.com.yurizp.notificationservice.dto.User;
import br.com.yurizp.notificationservice.dto.UserStub;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(
                jwtService, "SECRET_KEY", "59703373367639792442264528482B4D6251655468576D5A7134743777217A25");
    }

    @Test
    public void shouldCreateJtwToken() {
        User user = UserStub.create();

        JwtToken jwtToken = jwtService.generateToken(user);

        assertAll(
                () -> assertTrue(jwtToken.getIssuedAt().before(new Date())),
                () -> assertTrue(jwtToken.getExpiration().after(new Date())),
                () -> assertNotNull(jwtToken.getToken()));
    }

    @Test
    public void shouldDecodeToken() {
        User user = UserStub.create();
        String token = jwtService.generateToken(user).getToken();

        JwtToken jwtToken = jwtService.decodeToken(token);

        assertAll(
                () -> assertEquals(token, jwtToken.getToken()),
                () -> assertNotNull(jwtToken.getExpiration()),
                () -> assertNotNull(jwtToken.getIssuedAt()),
                () -> assertNotNull(jwtToken.getUser()),
                () -> assertEquals(user.getEmail(), jwtToken.getUser().getEmail()),
                () -> assertEquals(user.getId(), jwtToken.getUser().getId()));
    }
}
