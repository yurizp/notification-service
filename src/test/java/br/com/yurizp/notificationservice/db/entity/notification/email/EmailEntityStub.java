package br.com.yurizp.notificationservice.db.entity.notification.email;

import br.com.yurizp.notificationservice.db.entity.AppEntityStub;
import java.util.List;

import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomUtils;

public class EmailEntityStub {

    public static EmailEntity create() {
        return EmailEntity.builder()
                .id(RandomUtils.nextLong())
                .app(AppEntityStub.create())
                .sender(createSender())
                .server(createServer())
                .emailTemplates(createEmailTemplates())
                .build();
    }

    private static List<EmailTemplateEntity> createEmailTemplates() {
        return List.of(createEmailTemplate(), createEmailTemplate(), createEmailTemplate());
    }

    private static EmailTemplateEntity createEmailTemplate() {
        return EmailTemplateEntity.builder()
                .id(RandomUtils.nextLong())
                .name(new RandomString().nextString())
                .uri(new RandomString().nextString())
                .build();
    }

    private static ServerEntity createServer() {
        return ServerEntity.builder()
                .id(RandomUtils.nextLong())
                .userPassword(new RandomString().nextString())
                .userLogin(new RandomString().nextString())
                .smtpName(new RandomString().nextString())
                .smptPort(new RandomString().nextString())
                .build();
    }

    private static SenderEntity createSender() {
        return SenderEntity.builder()
                .id(RandomUtils.nextLong())
                .name(new RandomString().nextString())
                .email(new RandomString().nextString())
                .build();
    }
}
