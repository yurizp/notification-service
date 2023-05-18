package br.com.vibbra.notificationservice.controller.request.authentication;

import br.com.vibbra.notificationservice.config.tostring.Exclude;
import br.com.vibbra.notificationservice.config.tostring.Objects;
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

    @Exclude
    @NotBlank
    private String password;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
