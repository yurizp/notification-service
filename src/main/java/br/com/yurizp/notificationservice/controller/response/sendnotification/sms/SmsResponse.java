package br.com.yurizp.notificationservice.controller.response.sendnotification.sms;

import br.com.yurizp.commons.tostring.Objects;
import br.com.yurizp.notificationservice.controller.response.sendnotification.SendNotificationResponse;
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
public class SmsResponse extends SendNotificationResponse {

    @JsonProperty("channel")
    public Channel channel;

    @JsonProperty("phone_number")
    private List<String> phoneNumber;

    @JsonProperty("message_text")
    private String messageText;

    @JsonProperty("app_id")
    private String appId;

    public SmsResponse(Channel channel, List<String> phoneNumber, String messageText, String appId) {
        super(channel.name());
        this.phoneNumber = phoneNumber;
        this.messageText = messageText;
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
