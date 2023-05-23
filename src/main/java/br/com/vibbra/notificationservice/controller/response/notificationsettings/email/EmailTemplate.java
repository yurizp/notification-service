package br.com.vibbra.notificationservice.controller.response.notificationsettings.email;

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

    private String name;
    private String uri;
}
