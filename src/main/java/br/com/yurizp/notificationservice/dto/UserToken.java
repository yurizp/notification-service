package br.com.yurizp.notificationservice.dto;

import br.com.yurizp.commons.tostring.Objects;
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
public class UserToken {

    private Long id;
    private String email;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
