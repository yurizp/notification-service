package br.com.vibbra.notificationservice.controller.request.sendnotification;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import br.com.vibbra.notificationservice.controller.request.sendnotification.email.EmailRequest;
import br.com.vibbra.notificationservice.controller.request.sendnotification.sms.SmsRequest;
import br.com.vibbra.notificationservice.controller.request.sendnotification.webpush.WebpushRequest;
import br.com.vibbra.notificationservice.enums.Channel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "channel")
@JsonSubTypes({
    @JsonSubTypes.Type(value = WebpushRequest.class, name = "WEBPUSHES"),
    @JsonSubTypes.Type(value = SmsRequest.class, name = "SMS"),
    @JsonSubTypes.Type(value = EmailRequest.class, name = "EMAIL"),
})
public abstract class SendNotificationRequest {

    @JsonIgnore
    public String channel;

    public abstract Channel getChannel();

    protected SendNotificationRequest() {}

    protected SendNotificationRequest(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
