package br.com.vibbra.notificationservice.controller.request.notificationsettings.email;

import br.com.vibbra.notificationservice.enums.Channel;
import java.util.ArrayList;
import java.util.List;
import net.bytebuddy.utility.RandomString;

public class EmailRequestStub {

    public static EmailRequest create() {
        return EmailRequest.builder()
                .channel(Channel.EMAIL)
                .sender(createSender())
                .server(createServer())
                .emailTemplates(createEmailTemplates())
                .build();
    }

    private static List<EmailTemplate> createEmailTemplates() {
        ArrayList<EmailTemplate> emailTemplates = new ArrayList<>();
        emailTemplates.add(createEmailTemplate());
        emailTemplates.add(createEmailTemplate());
        emailTemplates.add(createEmailTemplate());
        return emailTemplates;
    }

    private static EmailTemplate createEmailTemplate() {
        return EmailTemplate.builder()
                .name("name" + new RandomString().nextString())
                .uri("uri" + new RandomString().nextString())
                .build();
    }

    private static Server createServer() {
        return Server.builder()
                .userPassword("userPassword" + new RandomString().nextString())
                .userLogin("userLogin" + new RandomString().nextString())
                .smtpName("smtpName" + new RandomString().nextString())
                .smptPort("smptPort" + new RandomString().nextString())
                .build();
    }

    private static Sender createSender() {
        return Sender.builder()
                .name("name" + new RandomString().nextString())
                .email("email" + new RandomString().nextString())
                .build();
    }
}
