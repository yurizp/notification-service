package br.com.vibbra.notificationservice.controller.request.authentication;

import br.com.vibbra.notificationservice.config.tostring.ToStringIgnore;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequest {

    private String id;
    private String username;

    @ToStringIgnore
    private String password;

    private String email;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
