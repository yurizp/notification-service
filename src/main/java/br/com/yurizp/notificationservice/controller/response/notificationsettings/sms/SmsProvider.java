package br.com.yurizp.notificationservice.controller.response.notificationsettings.sms;

import br.com.yurizp.commons.tostring.Objects;
import br.com.yurizp.commons.tostring.ToStringIgnore;
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
