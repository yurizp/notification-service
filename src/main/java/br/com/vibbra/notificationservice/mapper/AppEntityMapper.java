package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.controller.request.app.CreateAppRequest;
import br.com.vibbra.notificationservice.db.entity.AppEntity;
import br.com.vibbra.notificationservice.db.entity.UserEntity;
import java.util.UUID;

public class AppEntityMapper {
    public static AppEntity create(CreateAppRequest createApp, UserEntity user) {
        return AppEntity.builder()
                .name(createApp.getAppName())
                .token(UUID.randomUUID().toString())
                .user(user)
                .build();
    }
}
