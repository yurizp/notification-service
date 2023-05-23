package br.com.vibbra.notificationservice.controller.request.notificationsettings.webpush;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WelcomeNotification {

    @NotEmpty
    @JsonProperty("message_title")
    private String messageTitle;

    @NotEmpty
    @JsonProperty("message_text")
    private String messageText;

    @NotEmpty
    @JsonProperty("enable_url_redirect")
    private boolean enableUrlRedirect;

    @NotEmpty
    @JsonProperty("url_redirect")
    private String urlRedirect;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
