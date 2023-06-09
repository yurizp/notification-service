package br.com.yurizp.notificationservice.controller.response.sendnotification;

import br.com.yurizp.commons.tostring.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryNotification {

    @JsonProperty("notification")
    private List<NotificationHistoryResponse> history;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
