package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.controller.response.app.AppResponse;
import br.com.vibbra.notificationservice.db.entity.AppEntity;
import br.com.vibbra.notificationservice.db.entity.NotificationConfigEntity;
import br.com.vibbra.notificationservice.enums.Channel;
import io.jsonwebtoken.lang.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppResponseMapper {

    public static AppResponse create(AppEntity appEntity) {
        return AppResponse.builder()
                .appId(appEntity.getId())
                .appToken(appEntity.getToken())
                .appName(appEntity.getName())
                .activeChannels(createActiveChannels(appEntity.getNotificationConfigs()))
                .build();
    }

    private static Map<Channel, Boolean> createActiveChannels(List<NotificationConfigEntity> notificationConfigs) {
        if (Collections.isEmpty(notificationConfigs)) {
            return null;
        }
        return notificationConfigs.stream()
                .collect(Collectors.toMap(NotificationConfigEntity::getChannel, NotificationConfigEntity::isEnabled));
    }
}
