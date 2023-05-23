package br.com.vibbra.notificationservice.controller.response.notificationsettings.sms;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import br.com.vibbra.notificationservice.config.tostring.ToStringIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmsProvider {

    private String name;
    private String login;

    @ToStringIgnore
    private String password;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
