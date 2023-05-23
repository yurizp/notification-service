package br.com.yurizp.notificationservice.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import br.com.yurizp.notificationservice.controller.response.notificationsettings.SettingsResponse;
import br.com.yurizp.notificationservice.controller.response.notificationsettings.email.EmailResponse;
import br.com.yurizp.notificationservice.controller.response.notificationsettings.email.EmailTemplate;
import br.com.yurizp.notificationservice.db.entity.notification.email.EmailEntity;
import br.com.yurizp.notificationservice.db.entity.notification.email.EmailEntityStub;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class EmailResponseMapperTest {

    @Test
    public void shouldReturnSettingsResponse() {
        EmailEntity emailEntity = EmailEntityStub.create();
        List<EmailTemplate> emailTemplatesExpected = emailEntity.getEmailTemplates().stream()
                .map(emailTemplate -> EmailTemplate.builder()
                        .uri(emailTemplate.getUri())
                        .name(emailTemplate.getName())
                        .build())
                .collect(Collectors.toList());

        SettingsResponse settingsResponse = EmailResponseMapper.create(emailEntity);
        EmailResponse emailResponse = (EmailResponse) settingsResponse;

        assertAll(
                () -> assertNotNull(settingsResponse),
                () -> assertNotNull(emailEntity.getEmailTemplates()),
                () -> assertEquals(
                        emailEntity.getServer().getSmptPort(),
                        emailResponse.getServer().getSmptPort()),
                () -> assertEquals(
                        emailEntity.getServer().getUserLogin(),
                        emailResponse.getServer().getUserLogin()),
                () -> assertEquals(
                        emailEntity.getServer().getUserPassword(),
                        emailResponse.getServer().getUserPassword()),
                () -> assertEquals(
                        emailEntity.getServer().getSmtpName(),
                        emailResponse.getServer().getSmtpName()),
                () -> assertEquals(
                        emailEntity.getSender().getEmail(),
                        emailResponse.getSender().getEmail()),
                () -> assertEquals(
                        emailEntity.getSender().getName(),
                        emailResponse.getSender().getName()),
                () -> assertThat(emailResponse.getEmailTemplates())
                        .usingRecursiveComparison()
                        .isEqualTo(emailTemplatesExpected));
    }

    @Test
    public void shouldReturnSettingsResponseWithEmailTemplateNullWhenEntityIsNull() {
        EmailEntity emailEntity = EmailEntityStub.create();
        emailEntity.setEmailTemplates(null);

        SettingsResponse settingsResponse = EmailResponseMapper.create(emailEntity);
        EmailResponse emailResponse = (EmailResponse) settingsResponse;

        assertAll(
                () -> assertNotNull(settingsResponse),
                () -> assertEquals(
                        emailEntity.getServer().getSmptPort(),
                        emailResponse.getServer().getSmptPort()),
                () -> assertEquals(
                        emailEntity.getServer().getUserLogin(),
                        emailResponse.getServer().getUserLogin()),
                () -> assertEquals(
                        emailEntity.getServer().getUserPassword(),
                        emailResponse.getServer().getUserPassword()),
                () -> assertEquals(
                        emailEntity.getServer().getSmtpName(),
                        emailResponse.getServer().getSmtpName()),
                () -> assertEquals(
                        emailEntity.getSender().getEmail(),
                        emailResponse.getSender().getEmail()),
                () -> assertEquals(
                        emailEntity.getSender().getName(),
                        emailResponse.getSender().getName()),
                () -> assertNull(emailResponse.getEmailTemplates()));
    }
}
