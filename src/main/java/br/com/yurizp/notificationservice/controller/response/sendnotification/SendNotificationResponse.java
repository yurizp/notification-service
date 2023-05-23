package br.com.yurizp.notificationservice.controller.response.sendnotification;

import br.com.yurizp.commons.tostring.Objects;
import br.com.yurizp.notificationservice.enums.Channel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class SendNotificationResponse {

    @JsonIgnore
    public String channel;

    public abstract Channel getChannel();

    protected SendNotificationResponse() {}

    protected SendNotificationResponse(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
