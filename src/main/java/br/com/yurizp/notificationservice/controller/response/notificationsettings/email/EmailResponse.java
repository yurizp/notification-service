package br.com.yurizp.notificationservice.controller.response.notificationsettings.email;

import br.com.yurizp.commons.tostring.Objects;
import br.com.yurizp.notificationservice.controller.response.notificationsettings.SettingsResponse;
import br.com.yurizp.notificationservice.enums.Channel;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class EmailResponse extends SettingsResponse {

    public Channel channel;

    @JsonProperty("server")
    public Server server;

    @JsonProperty("sender")
    public Sender sender;

    @JsonProperty("email_templates")
    public List<EmailTemplate> emailTemplates;

    public EmailResponse(Channel channel, Server server, Sender sender, List<EmailTemplate> emailTemplates) {
        super(channel.name());
        this.channel = channel;
        this.server = server;
        this.sender = sender;
        this.emailTemplates = emailTemplates;
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
