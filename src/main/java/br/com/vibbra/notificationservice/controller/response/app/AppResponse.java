package br.com.vibbra.notificationservice.controller.response.app;

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
public class AppResponse {

    @JsonProperty("app_id")
    private Long appId;
    @JsonProperty("app_name")
    private String appName;
    @JsonProperty("app_token")
    private String appToken;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
