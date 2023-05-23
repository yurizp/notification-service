package br.com.vibbra.notificationservice.strategy.notification;

import br.com.vibbra.notificationservice.controller.request.notification.NotificationRequest;
import br.com.vibbra.notificationservice.controller.response.notification.NotificationResponse;
import br.com.vibbra.notificationservice.db.AppRepository;
import br.com.vibbra.notificationservice.db.EmailRepository;
import br.com.vibbra.notificationservice.db.EmailTemplateRepository;
import br.com.vibbra.notificationservice.db.NotificationConfigRepository;
import br.com.vibbra.notificationservice.db.entity.AppEntity;
import br.com.vibbra.notificationservice.db.entity.NotificationConfigEntity;
import br.com.vibbra.notificationservice.db.entity.notification.email.EmailEntity;
import br.com.vibbra.notificationservice.dto.NotificationConfigResponse;
import br.com.vibbra.notificationservice.enums.Channel;
import br.com.vibbra.notificationservice.exceptions.AppNotFoundException;
import br.com.vibbra.notificationservice.exceptions.SettingsNotFoundException;
import br.com.vibbra.notificationservice.mapper.EmailEntityMapper;
import br.com.vibbra.notificationservice.mapper.NotificationResponseMapper;
import br.com.vibbra.notificationservice.strategy.NotificationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class EmailNotificationStrategy extends BaseNotificationStrategy implements NotificationStrategy {

    private final AppRepository appRepository;
    private final EmailRepository emailRepository;
    private final NotificationConfigRepository configRepository;

    public EmailNotificationStrategy(
            AppRepository appRepository,
            NotificationConfigRepository configRepository,
            EmailRepository emailRepository) {
        super(appRepository, configRepository);
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
            AppEntity appEntity = appRepository.findByIdAndUserId(appId, userId).orElseThrow(() -> new AppNotFoundException());
            log.info("[Email Add/Updade Configuração] App encontrado {}", appEntity);
            Optional<EmailEntity> webpushEntity = emailRepository.findByAppId(appId);

            if (webpushEntity.isPresent()) {
                EmailEntity webpush = webpushEntity.get();
                log.info("[Email Configuração] Configuração encontrada {}", webpush);
                EmailEntity entity = webpush;
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
        log.info("[Email Add Configuração] Finalizado com sucesso o processo de configuração do Webpush {}", save);
    }

    private void updateSettings(EmailEntity entity, NotificationRequest notification) {
        log.info(
                "[Email Update Configuracao] Iniciando o processo de atualização de configuração. Email:{} Notification:{}",
                entity,
                notification);
        EmailEntity emailEntity = EmailEntityMapper.update(entity, notification);
        EmailEntity save = emailRepository.save(emailEntity);
        log.info(
                "[Email Update Configuracao] Finalizado o processo de atualização de configuração do Webpush {}",
                save);
    }

    @Override
    public Channel getChannel() {
        return Channel.EMAIL;
    }
}
