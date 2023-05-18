package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.controller.response.app.AppResponse;
import br.com.vibbra.notificationservice.db.entity.AppEntity;
import br.com.vibbra.notificationservice.db.entity.AppEntityStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppResponseMapperTest {

    @Test
    public void shouldCreateAppResponse(){
        AppEntity appEntity = AppEntityStub.create();

        AppResponse appResponse = AppResponseMapper.create(appEntity);

        assertAll(
                () -> assertEquals(appEntity.getId(), appResponse.getAppId()),
                () -> assertEquals(appEntity.getName(), appResponse.getAppName()),
                () -> assertEquals(appEntity.getToken(), appResponse.getAppToken())
        );
    }
}