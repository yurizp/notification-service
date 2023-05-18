package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.controller.request.app.CreateAppRequest;
import br.com.vibbra.notificationservice.controller.response.app.AppResponse;
import br.com.vibbra.notificationservice.db.entity.AppEntity;

public class AppResponseMapper {

    public static AppResponse create(AppEntity appEntity) {
        return AppResponse.builder()
                .appId(appEntity.getId())
                .appToken(appEntity.getToken())
                .appName(appEntity.getName())
                .build();
    }
}
