package br.com.yurizp.notificationservice.controller.request.notificationsettings.sms;

import br.com.yurizp.commons.tostring.Objects;
import br.com.yurizp.notificationservice.controller.request.notificationsettings.SettingsRequest;
import br.com.yurizp.notificationservice.enums.Channel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class SmsRequest extends SettingsRequest {

    @JsonProperty("channel")
    public Channel channel;

    @Valid
    @NotNull
    @JsonProperty("sms_provider")
    public SmsProvider site;

    public SmsRequest(Channel channel, @NotNull SmsProvider site) {
        super(channel.name());
        this.channel = channel;
        this.site = site;
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
