package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.controller.request.notificationsettings.NotificationRequest;
import br.com.vibbra.notificationservice.controller.request.notificationsettings.email.EmailRequest;
import br.com.vibbra.notificationservice.controller.request.notificationsettings.email.Sender;
import br.com.vibbra.notificationservice.controller.request.notificationsettings.email.Server;
import br.com.vibbra.notificationservice.db.entity.AppEntity;
import br.com.vibbra.notificationservice.db.entity.notification.email.EmailEntity;
import br.com.vibbra.notificationservice.db.entity.notification.email.EmailTemplateEntity;
import br.com.vibbra.notificationservice.db.entity.notification.email.SenderEntity;
import br.com.vibbra.notificationservice.db.entity.notification.email.ServerEntity;
import br.com.vibbra.notificationservice.exceptions.InvalidBodyException;
import io.jsonwebtoken.lang.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmailEntityMapper {

    public static EmailEntity create(NotificationRequest notification, AppEntity app) {
        if (!(notification.getSettings() instanceof EmailRequest)) {
            throw new InvalidBodyException();
        }
        EmailRequest emailRequest = (EmailRequest) notification.getSettings();
        return EmailEntity.builder()
                .app(app)
                .emailTemplates(createEmailTemplates(emailRequest))
                .server(createServer(emailRequest.getServer(), null))
                .sender(createSender(emailRequest.getSender(), null))
                .build();
    }

    public static EmailEntity update(EmailEntity email, NotificationRequest notification) {
        if (!(notification.getSettings() instanceof EmailRequest)) {
            throw new InvalidBodyException();
        }
        EmailRequest emailRequest = (EmailRequest) notification.getSettings();
        email.setEmailTemplates(new ArrayList<>(createEmailTemplates(emailRequest)));
        email.setServer(createServer(emailRequest.getServer(), email.getServer().getId()));
        email.setSender(createSender(emailRequest.getSender(), email.getSender().getId()));
        return email;
    }

    private static SenderEntity createSender(Sender sender, Long id) {
        return SenderEntity.builder()
                .id(id)
                .email(sender.getEmail())
                .name(sender.getName())
                .build();
    }

    private static ServerEntity createServer(Server server, Long id) {
        return ServerEntity.builder()
                .id(id)
                .smptPort(server.getSmptPort())
                .smtpName(server.getSmtpName())
                .userLogin(server.getUserLogin())
                .userPassword(server.getUserPassword())
                .build();
    }

    private static List<EmailTemplateEntity> createEmailTemplates(EmailRequest emailRequest) {
        if (Collections.isEmpty(emailRequest.getEmailTemplates())) {
            return null;
        }
        return emailRequest.getEmailTemplates().stream()
                .map(emailTemplate -> EmailTemplateEntity.builder()
                        .uri(emailTemplate.getUri())
                        .name(emailTemplate.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
