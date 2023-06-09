package br.com.yurizp.notificationservice.controller.request.notificationsettings.email;

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
public class EmailTemplate {

    @NotEmpty
    private String name;

    @NotEmpty
    private String uri;
}
