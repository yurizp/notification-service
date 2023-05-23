package br.com.yurizp.notificationservice.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import br.com.yurizp.notificationservice.controller.request.user.CreateUserRequest;
import br.com.yurizp.notificationservice.controller.request.user.CreateUserRequestStub;
import br.com.yurizp.notificationservice.db.entity.UserEntity;
import org.junit.jupiter.api.Test;

class UserEntityMapperTest {

    @Test
    public void shouldCreateUserEntityWhenRequestIsAllCompleted() {
        CreateUserRequest request = CreateUserRequestStub.create();

        UserEntity entity = UserEntityMapper.create(request);

        assertAll(
                () -> assertEquals(entity.getName(), request.getName()),
                () -> assertNotNull(request.getName()),
                () -> assertEquals(entity.getEmail(), request.getEmail()),
                () -> assertNotNull(request.getEmail()),
                () -> assertEquals(entity.getPassword(), request.getPassword()),
                () -> assertNotNull(request.getPassword()),
                () -> assertEquals(entity.getCompanyAddress(), request.getCompanyAddress()),
                () -> assertNotNull(request.getCompanyAddress()),
                () -> assertEquals(entity.getCompanyName(), request.getCompanyName()),
                () -> assertNotNull(request.getCompanyName()),
                () -> assertEquals(entity.getPhoneNumber(), request.getPhoneNumber()),
                () -> assertNotNull(request.getPhoneNumber()));
    }

    @Test
    public void shouldCreateUserEntityWhenRequestHaveJustRequeridFiels() {
        CreateUserRequest request = CreateUserRequestStub.createWithJustRequeridFields();

        UserEntity entity = UserEntityMapper.create(request);

        assertAll(
                () -> assertEquals(request.getName(), entity.getName()),
                () -> assertEquals(request.getEmail(), entity.getEmail()),
                () -> assertEquals(request.getPassword(), entity.getPassword()),
                () -> assertNull(entity.getCompanyAddress()),
                () -> assertNull(entity.getCompanyName()),
                () -> assertNull(entity.getPhoneNumber()));
    }
}
