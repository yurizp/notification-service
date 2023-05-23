package br.com.yurizp.notificationservice.mapper;

import br.com.yurizp.notificationservice.controller.request.app.CreateAppRequest;
import br.com.yurizp.notificationservice.db.entity.AppEntity;
import br.com.yurizp.notificationservice.db.entity.UserEntity;

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
