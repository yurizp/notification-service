package br.com.vibbra.notificationservice.controller.response.notificationsettings.webpush;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Site {

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonProperty("url_icon")
    private String urlIcon;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
