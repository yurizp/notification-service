package br.com.yurizp.notificationservice.controller.request.sendnotification.webpush;

import br.com.yurizp.commons.tostring.Objects;
import br.com.yurizp.notificationservice.controller.request.sendnotification.SendNotificationRequest;
import br.com.yurizp.notificationservice.enums.Channel;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WebpushRequest extends SendNotificationRequest {
    @JsonProperty("channel")
    public Channel channel;

    @JsonProperty("audience_segments")
    private List<String> audienceSegments;

    @JsonProperty("message_title")
    private String messageTitle;

    @JsonProperty("message_text")
    private String messageText;

    @JsonProperty("redirect_url")
    private String redirectUrl;

    @JsonProperty("icon_url")
    private String iconUrl;

    @JsonProperty("app_id")
    private String appId;

    @Override
    public String toString() {
        return Objects.toString(this);
    }

    @Override
    public Channel getChannel() {
        return channel;
    }
}
