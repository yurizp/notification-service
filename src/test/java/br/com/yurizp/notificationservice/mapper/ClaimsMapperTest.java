package br.com.yurizp.notificationservice.mapper;

import static org.junit.jupiter.api.Assertions.*;

import br.com.yurizp.notificationservice.dto.User;
import br.com.yurizp.notificationservice.dto.UserStub;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ClaimsMapperTest {

    @Test
    public void shouldMapperUserToClaimsSuccessfully() {
        User user = UserStub.create();
        Map<String, Object> claims = ClaimsMapper.create(user);

        assertAll(
                () -> assertEquals(user.getId(), claims.get("userId")),
                () -> assertEquals(user.getEmail(), claims.get("userEmail")),
                () -> assertEquals(2, claims.size()));
    }

    @Test
    public void shouldReturnEmptyMapWhenUserIsNull() {
        Map<String, Object> claims = ClaimsMapper.create(null);
        assertEquals(0, claims.size());
    }
}
