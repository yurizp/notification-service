package br.com.yurizp.notificationservice.controller.request.app;

import br.com.yurizp.commons.tostring.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
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
public class CreateAppRequest {

    @NotEmpty
    @JsonProperty("app_name")
    private String appName;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
