package br.com.vibbra.notificationservice.controller.response.notificationsettings.webpush;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WelcomeNotification {

    @JsonProperty("message_title")
    private String messageTitle;

    @JsonProperty("message_text")
    private String messageText;

    @JsonProperty("enable_url_redirect")
    private boolean enableUrlRedirect;

    @JsonProperty("url_redirect")
    private String urlRedirect;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
