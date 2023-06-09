package br.com.yurizp.notificationservice.strategy.notification;

import static java.time.temporal.ChronoUnit.DAYS;

import br.com.yurizp.notificationservice.controller.response.sendnotification.HistoryNotification;
import br.com.yurizp.notificationservice.controller.response.sendnotification.NotificationHistoryResponse;
import br.com.yurizp.notificationservice.db.AppRepository;
import br.com.yurizp.notificationservice.db.NotificationConfigRepository;
import br.com.yurizp.notificationservice.db.NotificationHistoryRepository;
import br.com.yurizp.notificationservice.db.entity.AppEntity;
import br.com.yurizp.notificationservice.db.entity.NotificationConfigEntity;
import br.com.yurizp.notificationservice.dto.NotificationConfigResponse;
import br.com.yurizp.notificationservice.enums.Channel;
import br.com.yurizp.notificationservice.exceptions.AppNotFoundException;
import br.com.yurizp.notificationservice.exceptions.GapBetweenDatesTooLongErrorException;
import br.com.yurizp.notificationservice.mapper.NotificationConfigEntityMapper;
import br.com.yurizp.notificationservice.mapper.NotificationHistoryEntityMapper;
import br.com.yurizp.notificationservice.mapper.NotificationHistoryResponseMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BaseNotificationStrategy {

    private final AppRepository appRepository;
    private final NotificationConfigRepository configRepository;
    private final NotificationHistoryRepository historyRepository;

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

    protected void saveHistory(AppEntity app, Channel channel) {
        this.historyRepository.save(NotificationHistoryEntityMapper.create(app, channel));
    }

    protected HistoryNotification getNotifications(
            Long userId, Long appId, Channel channel, LocalDate startDate, LocalDate endDate) {
        if (DAYS.between(startDate, endDate) > 90) {
            throw new GapBetweenDatesTooLongErrorException();
        }
        List<NotificationHistoryResponse> history = historyRepository
                .findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqualAndAppIdAndChannel(
                        startDate, endDate, appId, channel)
                .stream()
                .map(NotificationHistoryResponseMapper::create)
                .collect(Collectors.toList());

        return HistoryNotification.builder().history(history).build();
    }
}
