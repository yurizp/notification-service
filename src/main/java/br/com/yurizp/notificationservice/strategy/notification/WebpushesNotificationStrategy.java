package br.com.yurizp.notificationservice.strategy.notification;

import br.com.yurizp.notificationservice.controller.request.notificationsettings.NotificationRequest;
import br.com.yurizp.notificationservice.controller.request.sendnotification.SendNotificationRequest;
import br.com.yurizp.notificationservice.controller.response.notificationsettings.NotificationResponse;
import br.com.yurizp.notificationservice.controller.response.sendnotification.HistoryNotification;
import br.com.yurizp.notificationservice.db.AppRepository;
import br.com.yurizp.notificationservice.db.NotificationConfigRepository;
import br.com.yurizp.notificationservice.db.NotificationHistoryRepository;
import br.com.yurizp.notificationservice.db.WebpushRepository;
import br.com.yurizp.notificationservice.db.entity.AppEntity;
import br.com.yurizp.notificationservice.db.entity.NotificationConfigEntity;
import br.com.yurizp.notificationservice.db.entity.notification.webpush.WebpushEntity;
import br.com.yurizp.notificationservice.dto.NotificationConfigResponse;
import br.com.yurizp.notificationservice.enums.Channel;
import br.com.yurizp.notificationservice.exceptions.AppNotFoundException;
import br.com.yurizp.notificationservice.exceptions.SettingsNotFoundException;
import br.com.yurizp.notificationservice.mapper.NotificationResponseMapper;
import br.com.yurizp.notificationservice.mapper.WebpushesEntityMapper;
import br.com.yurizp.notificationservice.strategy.NotificationStrategy;
import java.time.LocalDate;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WebpushesNotificationStrategy extends BaseNotificationStrategy implements NotificationStrategy {

    private final AppRepository appRepository;
    private final WebpushRepository webpushRepository;
    private final NotificationConfigRepository configRepository;

    public WebpushesNotificationStrategy(
            AppRepository appRepository,
            NotificationConfigRepository configRepository,
            WebpushRepository webpushRepository,
            NotificationHistoryRepository notificationHistoryRepository) {
        super(appRepository, configRepository, notificationHistoryRepository);
        this.appRepository = appRepository;
        this.webpushRepository = webpushRepository;
        this.configRepository = configRepository;
    }

    @Override
    public void saveOrUpdateSettings(Long userId, Long appId, Channel channel, NotificationRequest notification) {
        try {
            log.info(
                    "[Webpush Add/Updade Configuração] Iniciando a configuração do usariaId {} appId {} e Canal {}",
                    userId,
                    appId,
                    channel);
            AppEntity appEntity =
                    appRepository.findByIdAndUserId(appId, userId).orElseThrow(() -> new AppNotFoundException());
            log.info("[Webpush Add/Updade Configuração] App encontrado {}", appEntity);
            Optional<WebpushEntity> webpushEntity = webpushRepository.findByAppId(appId);

            if (webpushEntity.isPresent()) {
                WebpushEntity webpush = webpushEntity.get();
                log.info("[Webpush Configuração] Configuração encontrada {}", webpush);
                WebpushEntity entity = webpush;
                updateSettings(entity, notification);
            } else {
                log.info(
                        "[Webpush Configuração] Configuração não encontrada. usariaId {} appId {} e Canal {}",
                        userId,
                        appId,
                        channel);
                createSettings(appEntity, notification);
            }
        } catch (Exception e) {
            log.error(
                    "[Webpush Add/Updade Configuração] Não foi ajustar as configuracoes para o app {} e userId {} error {}",
                    appId,
                    userId,
                    e);
            throw e;
        }
    }

    @Override
    public NotificationConfigResponse enableOrDisableNotification(Long userId, Long appId, Channel channel) {
        validateIfUserHavePermissionToAppAndIfExistsApp(userId, appId);

        Optional<NotificationConfigEntity> notificationConfig = configRepository.findByAppIdAndChannel(appId, channel);
        return notificationConfig.isPresent()
                ? super.updateConfig(notificationConfig.get())
                : super.createConfig(appId, userId, channel);
    }

    @Override
    public NotificationResponse findConfig(Long userId, Long appId, Channel channel) {
        try {
            log.info(
                    "[Webpush Busca Config] Iniciando a busca da config do usariaId {} appId {} e Canal {}",
                    userId,
                    appId,
                    channel);
            validateIfUserHavePermissionToAppAndIfExistsApp(userId, appId);
            WebpushEntity webpushEntity = webpushRepository.findByAppId(appId).get();
            NotificationResponse notificationResponse = NotificationResponseMapper.create(webpushEntity);
            log.info("[Webpush Busca Config] Retornado com sucesso a config {}", notificationResponse);
            return notificationResponse;
        } catch (Exception e) {
            log.error(
                    "[Webpush Busca Config] Ocorreu um erro ao buscar a config usariaId {} appId {} e Canal {} error:{}",
                    userId,
                    appId,
                    channel,
                    e);
            throw e;
        }
    }

    @Override
    public void sendNotification(
            Long userId, Long appId, Channel channel, SendNotificationRequest sendNotificationRequest) {
        validateIfUserHavePermissionToAppAndIfExistsApp(userId, appId);
        log.info(
                "[Webpush Send Notification] Iniciando o envio de notificação para o usariaId {} appId {} e Canal {}",
                userId,
                appId,
                channel);

        AppEntity appEntity =
                appRepository.findByIdAndUserId(appId, userId).orElseThrow(() -> new AppNotFoundException());
        super.saveHistory(appEntity, channel);
    }

    @Override
    public HistoryNotification getNotifications(
            Long userId, Long appId, Channel channel, LocalDate startDate, LocalDate endDate) {
        validateIfUserHavePermissionToAppAndIfExistsApp(userId, appId);
        return super.getNotifications(userId, appId, channel, startDate, endDate);
    }

    private void validateIfUserHavePermissionToAppAndIfExistsApp(Long userId, Long appId) {
        log.info("[Webpush Validacao] Validando se o usariaId {} tem permissão para o appId {}", userId, appId);
        if (!appRepository.existsByIdAndUserId(appId, userId)) {
            log.error("[Webpush Validacao] App não encontrado para o usariaId {} appId {}", userId, appId);
            throw new AppNotFoundException();
        }

        if (!webpushRepository.existsByAppId(appId)) {
            log.error("[Webpush Validacao] Configuração não encontrada para o appId {}", appId);
            throw new SettingsNotFoundException();
        }
        log.info("[Webpush Validacao] UsariaId {} tem permissão para o appId {}", userId, appId);
    }

    private void createSettings(AppEntity app, NotificationRequest notification) {
        log.info(
                "[Webpush Add Configuração] Iniciando o processo de criacção de configuração. Webpush:{} Notification:{}",
                app,
                notification);
        WebpushEntity webPushEntity = WebpushesEntityMapper.create(notification, app);
        WebpushEntity save = webpushRepository.save(webPushEntity);
        log.info("[Webpush Add Configuração] Finalizado com sucesso o processo de configuração do Webpush {}", save);
    }

    private void updateSettings(WebpushEntity webpush, NotificationRequest notification) {
        log.info(
                "[Webpush Update Configuracao] Iniciando o processo de atualização de configuração. Webpush:{} Notification:{}",
                webpush,
                notification);
        WebpushEntity webPushEntity = WebpushesEntityMapper.update(webpush, notification);
        WebpushEntity save = webpushRepository.save(webPushEntity);
        log.info(
                "[Webpush Update Configuracao] Finalizado o processo de atualização de configuração do Webpush {}",
                save);
    }

    @Override
    public Channel getChannel() {
        return Channel.WEBPUSHES;
    }
}
