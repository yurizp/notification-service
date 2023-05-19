package br.com.vibbra.notificationservice.dto;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class NotificationConfigResponse {

    @JsonProperty("previous_status")
    private boolean previousStatus;

    @JsonProperty("current_status")
    private boolean currentStatus;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
