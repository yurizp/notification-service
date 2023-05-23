package br.com.yurizp.notificationservice.dto;

import br.com.yurizp.commons.tostring.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
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
