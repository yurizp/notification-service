package br.com.vibbra.notificationservice.controller.request.notification.email;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty
    private String name;
    @NotEmpty
    private String email;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
