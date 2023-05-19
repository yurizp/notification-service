package br.com.vibbra.notificationservice.mapper;

import static org.junit.jupiter.api.Assertions.*;

import br.com.vibbra.notificationservice.controller.request.app.CreateAppRequest;
import br.com.vibbra.notificationservice.db.entity.AppEntity;
import br.com.vibbra.notificationservice.db.entity.UserEntity;
import br.com.vibbra.notificationservice.db.entity.UserEntityStub;
import org.junit.jupiter.api.Test;

class AppEntityMapperTest {

    @Test
    public void shouldReturnAppEntity() {
        CreateAppRequest request =
                CreateAppRequest.builder().appName("app name").build();
        UserEntity userEntity = UserEntityStub.create();

        AppEntity appEntity = AppEntityMapper.create(request, userEntity);

        assertAll(
                () -> assertEquals(appEntity.getId(), appEntity.getId()),
                () -> assertEquals(appEntity.getName(), appEntity.getName()),
                () -> assertEquals(appEntity.getUser(), appEntity.getUser()),
                () -> assertEquals(appEntity.getToken(), appEntity.getToken()));
    }
}
