package br.com.yurizp.notificationservice.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.yurizp.notificationservice.controller.request.app.CreateAppRequest;
import br.com.yurizp.notificationservice.controller.response.app.AppResponse;
import br.com.yurizp.notificationservice.db.AppRepository;
import br.com.yurizp.notificationservice.db.UserRepository;
import br.com.yurizp.notificationservice.db.entity.AppEntity;
import br.com.yurizp.notificationservice.db.entity.AppEntityStub;
import br.com.yurizp.notificationservice.db.entity.UserEntity;
import br.com.yurizp.notificationservice.db.entity.UserEntityStub;
import br.com.yurizp.notificationservice.exceptions.AppNotFoundException;
import br.com.yurizp.notificationservice.exceptions.UserNotFoundException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AppServiceTest {

    @InjectMocks
    private AppService app;

    @Mock
    private AppRepository appRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void shouldCreateApp() {
        UserEntity userEntity = UserEntityStub.create();
        AppEntity appEntity = AppEntityStub.create();
        CreateAppRequest appRequest =
                CreateAppRequest.builder().appName("app name").build();

        when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
        when(appRepository.save(any())).thenReturn(appEntity);

        AppResponse appResponse = app.createApp(appRequest, 1L);

        assertAll(() -> assertEquals(appResponse.getAppId(), appEntity.getId()));
    }

    @Test
    public void shouldThrowErrorWhenNotFindUserToCreateApp() {
        CreateAppRequest appRequest =
                CreateAppRequest.builder().appName("app name").build();

        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> app.createApp(appRequest, 1L));
    }

    @Test
    public void shouldThrowErrorWhenNotFindUserToGetAppById() {
        when(appRepository.findByIdAndUserId(any(), any())).thenReturn(Optional.empty());

        assertThrows(AppNotFoundException.class, () -> app.findAppById(123L, 1L));
    }

    @Test
    public void shouldReturnAppWhenFindById() {
        AppEntity appEntity = AppEntityStub.create();

        when(appRepository.findByIdAndUserId(any(), any())).thenReturn(Optional.of(appEntity));

        AppResponse appResponse = app.findAppById(123L, 1L);

        assertAll(() -> assertEquals(appResponse.getAppId(), appEntity.getId()));
    }
}
