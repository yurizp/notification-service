package br.com.yurizp.notificationservice.controller.response.notificationsettings.email;

import br.com.yurizp.commons.tostring.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Server {

    @JsonProperty("smtp_name")
    private String smtpName;

    @JsonProperty("smpt_port")
    private String smptPort;

    @JsonProperty("user_login")
    private String userLogin;

    @JsonProperty("user_password")
    private String userPassword;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
