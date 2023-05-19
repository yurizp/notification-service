package br.com.vibbra.notificationservice.strategy.notification;

import br.com.vibbra.notificationservice.db.AppRepository;
import br.com.vibbra.notificationservice.db.NotificationConfigRepository;
import br.com.vibbra.notificationservice.db.entity.AppEntity;
import br.com.vibbra.notificationservice.db.entity.NotificationConfigEntity;
import br.com.vibbra.notificationservice.dto.NotificationConfigResponse;
import br.com.vibbra.notificationservice.enums.Channel;
import br.com.vibbra.notificationservice.exceptions.AppNotFoundException;
import br.com.vibbra.notificationservice.mapper.NotificationConfigEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BaseNotificationStrategy {

    private final AppRepository appRepository;
    private final NotificationConfigRepository configRepository;

    protected NotificationConfigResponse updateConfig(NotificationConfigEntity notificationConfig) {
        final boolean previousStatus = notificationConfig.isEnabled();
        final boolean newValue = !previousStatus;

        notificationConfig.setEnabled(newValue);
        NotificationConfigEntity save = configRepository.save(notificationConfig);

        log.info("[Webpush Configuração] Finalizado com sucesso o processo de configuração do Webpush {}", save);
        return NotificationConfigResponse.builder()
                .currentStatus(newValue)
                .previousStatus(previousStatus)
                .build();
    }

    protected NotificationConfigResponse createConfig(final Long appId, final Long userId, Channel channel) {
        AppEntity app = appRepository.findByIdAndUserId(appId, userId).orElseThrow(() -> new AppNotFoundException());
        NotificationConfigEntity webpushNotification = NotificationConfigEntityMapper.create(app, channel);
        NotificationConfigEntity save = configRepository.save(webpushNotification);
        log.info("[Webpush Configuração] Finalizado com sucesso o processo de configuração do Webpush {}", save);
        return NotificationConfigResponse.builder()
                .currentStatus(save.isEnabled())
                .previousStatus(false)
                .build();
    }
}
