package br.com.yurizp.notificationservice.controller.request.notificationsettings.webpush;

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
public class WebPushRequest extends SettingsRequest {

    @JsonProperty("channel")
    public Channel channel;

    @Valid
    @NotNull
    @JsonProperty("site")
    public Site site;

    @Valid
    @NotNull
    @JsonProperty("allow_notification")
    public AllowNotification allowNotification;

    @JsonProperty("welcome_notification")
    public WelcomeNotification welcomeNotification;

    public WebPushRequest(
            Channel channel, Site site, AllowNotification allowNotification, WelcomeNotification welcomeNotification) {
        super(channel.name());
        this.channel = channel;
        this.site = site;
        this.allowNotification = allowNotification;
        this.welcomeNotification = welcomeNotification;
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
