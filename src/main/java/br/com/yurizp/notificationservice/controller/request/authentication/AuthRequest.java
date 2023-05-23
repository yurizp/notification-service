package br.com.yurizp.notificationservice.controller.request.authentication;

import br.com.yurizp.commons.tostring.Objects;
import br.com.yurizp.commons.tostring.ToStringIgnore;
import jakarta.validation.constraints.NotBlank;
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
public class AuthRequest {

    @NotBlank
    private String email;

    @NotBlank
    @ToStringIgnore
    private String password;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
