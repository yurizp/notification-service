package br.com.yurizp.notificationservice.controller.response.notificationsettings;

import br.com.yurizp.notificationservice.enums.Channel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class SettingsResponse {

    @JsonIgnore
    public String channel;

    public abstract Channel getChannel();

    protected SettingsResponse() {}

    protected SettingsResponse(String channel) {
        this.channel = channel;
    }
}
