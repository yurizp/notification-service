package br.com.vibbra.notificationservice.controller.request.notificationsettings.email;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Server {

    @NotEmpty
    @JsonProperty("smtp_name")
    private String smtpName;

    @NotEmpty
    @JsonProperty("smpt_port")
    private String smptPort;

    @NotEmpty
    @JsonProperty("user_login")
    private String userLogin;

    @NotEmpty
    @JsonProperty("user_password")
    private String userPassword;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
