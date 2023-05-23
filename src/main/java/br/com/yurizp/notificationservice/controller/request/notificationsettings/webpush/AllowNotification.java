package br.com.yurizp.notificationservice.controller.request.notificationsettings.webpush;

import br.com.yurizp.commons.tostring.Objects;
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
public class AllowNotification {

    @NotEmpty
    @JsonProperty("message_text")
    private String messageText;

    @NotEmpty
    @JsonProperty("allow_button_text")
    private String allowButtonText;

    @NotEmpty
    @JsonProperty("deny_button_text")
    private String denyButtonText;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
