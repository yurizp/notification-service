package br.com.vibbra.notificationservice.controller.request.notificationsettings;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
public class NotificationRequest {

    @Valid
    @NotNull
    public SettingsRequest settings;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
