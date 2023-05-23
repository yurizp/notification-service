package br.com.vibbra.notificationservice.controller.request.notification;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import br.com.vibbra.notificationservice.controller.request.notification.email.EmailRequest;
import br.com.vibbra.notificationservice.controller.request.notification.webpush.WebPushRequest;
import br.com.vibbra.notificationservice.enums.Channel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "channel")
@JsonSubTypes({
    @JsonSubTypes.Type(value = WebPushRequest.class, name = "WEBPUSHES"),
    @JsonSubTypes.Type(value = EmailRequest.class, name = "EMAIL"),
})
public abstract class SettingsRequest {

    @JsonIgnore
    public String channel;

    public abstract Channel getChannel();

    protected SettingsRequest() {}

    protected SettingsRequest(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
