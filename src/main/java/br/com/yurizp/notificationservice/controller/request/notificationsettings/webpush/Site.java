package br.com.yurizp.notificationservice.controller.request.notificationsettings.webpush;

import br.com.yurizp.commons.tostring.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Site {

    @NotEmpty
    @JsonProperty("name")
    private String name;

    @NotEmpty
    @JsonProperty("address")
    private String address;

    @NotEmpty
    @JsonProperty("url_icon")
    private String urlIcon;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
