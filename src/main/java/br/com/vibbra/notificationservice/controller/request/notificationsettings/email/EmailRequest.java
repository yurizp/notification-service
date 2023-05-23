package br.com.vibbra.notificationservice.controller.request.notificationsettings.email;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import br.com.vibbra.notificationservice.controller.request.notificationsettings.SettingsRequest;
import br.com.vibbra.notificationservice.enums.Channel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class EmailRequest extends SettingsRequest {

    public Channel channel;

    @Valid
    @NotNull
    @JsonProperty("sever")
    public Server server;

    @Valid
    @NotNull
    @JsonProperty("sender")
    public Sender sender;

    @Valid
    @NotNull
    @JsonProperty("email_templates")
    public List<EmailTemplate> emailTemplates;

    public EmailRequest(Channel channel, Server server, Sender sender, List<EmailTemplate> emailTemplates) {
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
