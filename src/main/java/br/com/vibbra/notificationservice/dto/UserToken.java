package br.com.vibbra.notificationservice.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserToken {

    private Long id;
    private String email;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
