package br.com.yurizp.notificationservice.mapper;

import br.com.yurizp.notificationservice.controller.response.notificationsettings.SettingsResponse;
import br.com.yurizp.notificationservice.controller.response.notificationsettings.email.EmailResponse;
import br.com.yurizp.notificationservice.controller.response.notificationsettings.email.EmailTemplate;
import br.com.yurizp.notificationservice.controller.response.notificationsettings.email.Sender;
import br.com.yurizp.notificationservice.controller.response.notificationsettings.email.Server;
import br.com.yurizp.notificationservice.db.entity.notification.email.EmailEntity;
import br.com.yurizp.notificationservice.db.entity.notification.email.EmailTemplateEntity;
import br.com.yurizp.notificationservice.db.entity.notification.email.SenderEntity;
import br.com.yurizp.notificationservice.db.entity.notification.email.ServerEntity;
import br.com.yurizp.notificationservice.enums.Channel;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class EmailResponseMapper {
    public static SettingsResponse create(EmailEntity webpushEntity) {
        return EmailResponse.builder()
                .channel(Channel.EMAIL)
                .sender(createSender(webpushEntity.getSender()))
                .server(createServer(webpushEntity.getServer()))
                .emailTemplates(createEmailTemplates(webpushEntity.getEmailTemplates()))
                .build();
    }

    private static List<EmailTemplate> createEmailTemplates(List<EmailTemplateEntity> emailTemplates) {
        if (CollectionUtils.isEmpty(emailTemplates)) {
            return null;
        }
        return emailTemplates.stream()
                .map(response -> EmailTemplate.builder()
                        .name(response.getName())
                        .uri(response.getUri())
                        .build())
                .collect(Collectors.toList());
    }

    private static Server createServer(ServerEntity server) {
        return Server.builder()
                .smptPort(server.getSmptPort())
                .smtpName(server.getSmtpName())
                .userPassword(server.getUserPassword())
                .userLogin(server.getUserLogin())
                .build();
    }

    private static Sender createSender(SenderEntity sender) {
        return Sender.builder().email(sender.getEmail()).name(sender.getName()).build();
    }
}
