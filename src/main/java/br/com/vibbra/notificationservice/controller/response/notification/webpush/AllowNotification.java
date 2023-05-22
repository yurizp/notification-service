package br.com.vibbra.notificationservice.controller.response.notification.webpush;

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
public class AllowNotification {

    @JsonProperty("message_text")
    private String messageText;

    @JsonProperty("allow_button_text")
    private String allowButtonText;

    @JsonProperty("deny_button_text")
    private String denyButtonText;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}