package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.controller.request.notification.NotificationRequest;
import br.com.vibbra.notificationservice.controller.request.notification.NotificationRequestStub;
import br.com.vibbra.notificationservice.controller.request.notification.email.EmailRequest;
import br.com.vibbra.notificationservice.db.entity.AppEntity;
import br.com.vibbra.notificationservice.db.entity.AppEntityStub;
import br.com.vibbra.notificationservice.db.entity.notification.email.EmailEntity;
import br.com.vibbra.notificationservice.db.entity.notification.email.EmailTemplateEntity;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmailEntityMapperTest {

    @Test
    public void shouldCreateEmailEntity(){
        AppEntity appEntity = AppEntityStub.create();
        NotificationRequest request = NotificationRequestStub.createEmailRequest();
        EmailRequest emailRequest = (EmailRequest) request.getSettings();
        List<EmailTemplateEntity> emailTemplatesExpected = emailRequest.getEmailTemplates()
                .stream()
                .map(emailTemplate -> EmailTemplateEntity.builder().id(null).uri(emailTemplate.getUri()).name(emailTemplate.getName()).build())
                .collect(Collectors.toList());

        EmailEntity email = EmailEntityMapper.create(request, appEntity);

        assertAll(
                () -> assertNotNull(email),
                () -> assertNotNull(email.getEmailTemplates()),
                () -> assertEquals(appEntity, email.getApp()),
                () -> assertEquals(emailRequest.getServer().getSmptPort(), email.getServer().getSmptPort()),
                () -> assertEquals(emailRequest.getServer().getUserLogin(), email.getServer().getUserLogin()),
                () -> assertEquals(emailRequest.getServer().getUserPassword(), email.getServer().getUserPassword()),
                () -> assertEquals(emailRequest.getServer().getSmtpName(), email.getServer().getSmtpName()),
                () -> assertEquals(emailRequest.getSender().getEmail(), email.getSender().getEmail()),
                () -> assertEquals(emailRequest.getSender().getName(), email.getSender().getName()),
                () -> assertThat(email.getEmailTemplates()).usingRecursiveComparison().isEqualTo(emailTemplatesExpected)
        );

    }

}