package br.com.vibbra.notificationservice.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import br.com.vibbra.notificationservice.controller.response.UserResponse;
import br.com.vibbra.notificationservice.dto.User;
import br.com.vibbra.notificationservice.dto.UserStub;
import org.junit.jupiter.api.Test;

class UserResposeMapperTest {

    @Test
    public void shouldCreateUserResponse() {
        User user = UserStub.create();

        UserResponse response = UserResposeMapper.create(user);

        assertAll(
                () -> assertEquals(user.getId(), response.getId()),
                () -> assertEquals(user.getName(), response.getName()),
                () -> assertEquals(user.getEmail(), response.getEmail()),
                () -> assertEquals(user.getPhoneNumber(), response.getPhoneNumber()),
                () -> assertEquals(user.getCompanyName(), response.getCompanyName()),
                () -> assertEquals(user.getCompanyAddress(), response.getCompanyAddress()));
    }

    @Test
    public void shouldCreateUserResponseWithJustRequeridFields() {
        User user = UserStub.createWithJustRequeridFields();

        UserResponse response = UserResposeMapper.create(user);

        assertAll(
                () -> assertEquals(user.getId(), response.getId()),
                () -> assertEquals(user.getName(), response.getName()),
                () -> assertEquals(user.getEmail(), response.getEmail()),
                () -> assertNull(response.getPhoneNumber()),
                () -> assertNull(response.getCompanyName()),
                () -> assertNull(response.getCompanyAddress()));
    }
}
