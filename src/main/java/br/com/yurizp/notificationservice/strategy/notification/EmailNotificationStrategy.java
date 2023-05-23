package br.com.yurizp.notificationservice.strategy.notification;

import br.com.yurizp.notificationservice.controller.request.notificationsettings.NotificationRequest;
import br.com.yurizp.notificationservice.controller.request.sendnotification.SendNotificationRequest;
import br.com.yurizp.notificationservice.controller.response.notificationsettings.NotificationResponse;
import br.com.yurizp.notificationservice.controller.response.sendnotification.HistoryNotification;
import br.com.yurizp.notificationservice.db.AppRepository;
import br.com.yurizp.notificationservice.db.EmailRepository;
import br.com.yurizp.notificationservice.db.NotificationConfigRepository;
import br.com.yurizp.notificationservice.db.NotificationHistoryRepository;
import br.com.yurizp.notificationservice.db.entity.AppEntity;
import br.com.yurizp.notificationservice.db.entity.NotificationConfigEntity;
import br.com.yurizp.notificationservice.db.entity.notification.email.EmailEntity;
import br.com.yurizp.notificationservice.dto.NotificationConfigResponse;
import br.com.yurizp.notificationservice.enums.Channel;
import br.com.yurizp.notificationservice.exceptions.AppNotFoundException;
import br.com.yurizp.notificationservice.exceptions.SettingsNotFoundException;
import br.com.yurizp.notificationservice.mapper.EmailEntityMapper;
import br.com.yurizp.notificationservice.mapper.NotificationResponseMapper;
import br.com.yurizp.notificationservice.strategy.NotificationStrategy;
import java.time.LocalDate;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailNotificationStrategy extends BaseNotificationStrategy implements NotificationStrategy {

    private final AppRepository appRepository;
    private final EmailRepository emailRepository;
    private final NotificationConfigRepository configRepository;

    public EmailNotificationStrategy(
            AppRepository appRepository,
            NotificationConfigRepository configRepository,
            EmailRepository emailRepository,
            NotificationHistoryRepository historyRepository) {
        super(appRepository, configRepository, historyRepository);
        this.appRepository = appRepository;
        this.emailRepository = emailRepository;
        this.configRepository = configRepository;
    }

    @Override
    public void saveOrUpdateSettings(Long userId, Long appId, Channel channel, NotificationRequest notification) {
        try {
            log.info(
                    "[Email Add/Updade Configuração] Iniciando a configuração do usariaId {} appId {} e Canal {}",
                    userId,
                    appId,
                    channel);
            AppEntity appEntity =
                    appRepository.findByIdAndUserId(appId, userId).orElseThrow(() -> new AppNotFoundException());
            log.info("[Email Add/Updade Configuração] App encontrado {}", appEntity);
            Optional<EmailEntity> entityPresent = emailRepository.findByAppId(appId);

            if (entityPresent.isPresent()) {
                EmailEntity emailEntity = entityPresent.get();
                log.info("[Email Configuração] Configuração encontrada {}", emailEntity);
                EmailEntity entity = emailEntity;
                updateSettings(entity, notification);
            } else {
                log.info(
                        "[Email Configuração] Configuração não encontrada. usariaId {} appId {} e Canal {}",
                        userId,
                        appId,
                        channel);
                createSettings(appEntity, notification);
            }
        } catch (Exception e) {
            log.error(
                    "[Email Add/Updade Configuração] Não foi ajustar as configuracoes para o app {} e userId {} error {}",
                    appId,
                    userId,
                    e.getMessage());
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
                    "[Email Busca Config] Iniciando a busca da config do usariaId {} appId {} e Canal {}",
                    userId,
                    appId,
                    channel);
            validateIfUserHavePermissionToAppAndIfExistsApp(userId, appId);
            EmailEntity email = emailRepository.findByAppId(appId).get();
            NotificationResponse notificationResponse = NotificationResponseMapper.create(email);
            log.info("[Email Busca Config] Retornado com sucesso a config {}", notificationResponse);
            return notificationResponse;
        } catch (Exception e) {
            log.error(
                    "[Email Busca Config] Ocorreu um erro ao buscar a config usariaId {} appId {} e Canal {} error:{}",
                    userId,
                    appId,
                    channel,
                    e.getMessage());
            throw e;
        }
    }

    @Override
    public void sendNotification(
            Long userId, Long appId, Channel channel, SendNotificationRequest sendNotificationRequest) {
        validateIfUserHavePermissionToAppAndIfExistsApp(userId, appId);
        log.info(
                "[Email Send Notification] Iniciando o envio de notificação para o usariaId {} appId {} e Canal {}",
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
        log.info("[Email Validacao] Validando se o usariaId {} tem permissão para o appId {}", userId, appId);
        if (!appRepository.existsByIdAndUserId(appId, userId)) {
            log.error("[Email Validacao] App não encontrado para o usariaId {} appId {}", userId, appId);
            throw new AppNotFoundException();
        }

        if (!emailRepository.existsByAppId(appId)) {
            log.error("[Email Validacao] Configuração não encontrada para o appId {}", appId);
            throw new SettingsNotFoundException();
        }
        log.info("[Email Validacao] UsariaId {} tem permissão para o appId {}", userId, appId);
    }

    private void createSettings(AppEntity app, NotificationRequest notification) {
        log.info(
                "[Email Add Configuração] Iniciando o processo de criacção de configuração. Email:{} Notification:{}",
                app,
                notification);
        EmailEntity email = EmailEntityMapper.create(notification, app);
        EmailEntity save = emailRepository.save(email);
        log.info("[Email Add Configuração] Finalizado com sucesso o processo de configuração {}", save);
    }

    private void updateSettings(EmailEntity entity, NotificationRequest notification) {
        log.info(
                "[Email Update Configuracao] Iniciando o processo de atualização de configuração. Email:{} Notification:{}",
                entity,
                notification);
        EmailEntity emailEntity = EmailEntityMapper.update(entity, notification);
        EmailEntity save = emailRepository.save(emailEntity);
        log.info("[Email Update Configuracao] Finalizado o processo de atualização de configuração {}", save);
    }

    @Override
    public Channel getChannel() {
        return Channel.EMAIL;
    }
}
