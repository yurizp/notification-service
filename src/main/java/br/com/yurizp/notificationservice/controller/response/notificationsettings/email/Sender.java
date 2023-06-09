package br.com.yurizp.notificationservice.controller.response.notificationsettings.email;

import br.com.yurizp.commons.tostring.Objects;
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
public class Sender {

    private String name;
    private String email;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
