package br.com.vibbra.notificationservice.controller.response.notification.webpush;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import br.com.vibbra.notificationservice.controller.response.notification.SettingsResponse;
import br.com.vibbra.notificationservice.enums.Channel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebpushResponse extends SettingsResponse {

    @JsonProperty("channel")
    public Channel channel;

    @JsonProperty("site")
    public Site site;

    @JsonProperty("allow_notification")
    public AllowNotification allowNotification;

    @JsonProperty("welcome_notification")
    public WelcomeNotification welcomeNotification;

    public WebpushResponse(
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
