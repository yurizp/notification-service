package br.com.yurizp.notificationservice.mapper;

import br.com.yurizp.notificationservice.controller.response.app.AppResponse;
import br.com.yurizp.notificationservice.db.entity.AppEntity;
import br.com.yurizp.notificationservice.db.entity.NotificationConfigEntity;
import br.com.yurizp.notificationservice.enums.Channel;
import org.springframework.util.CollectionUtils;

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
        if (CollectionUtils.isEmpty(notificationConfigs)) {
            return null;
        }
        return notificationConfigs.stream()
                .collect(Collectors.toMap(NotificationConfigEntity::getChannel, NotificationConfigEntity::isEnabled));
    }
}
