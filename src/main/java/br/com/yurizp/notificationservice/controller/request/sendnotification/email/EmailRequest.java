package br.com.yurizp.notificationservice.controller.request.sendnotification.email;

import br.com.yurizp.commons.tostring.Objects;
import br.com.yurizp.notificationservice.controller.request.sendnotification.SendNotificationRequest;
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
public class EmailRequest extends SendNotificationRequest {

    @JsonProperty("channel")
    public Channel channel;

    @JsonProperty("receiver_email")
    private List<String> receiverEmail;

    @JsonProperty("emailTemplateName")
    private String emailTemplateName;

    @JsonProperty("app_id")
    private String appId;

    public EmailRequest(Channel channel, List<String> receiverEmail, String emailTemplateName, String appId) {
        super(channel.name());
        this.channel = channel;
        this.receiverEmail = receiverEmail;
        this.emailTemplateName = emailTemplateName;
        this.appId = appId;
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }

    @Override
    public Channel getChannel() {
        return channel;
    }
}
