package br.com.yurizp.notificationservice.dto;

import br.com.yurizp.commons.tostring.Objects;
import br.com.yurizp.commons.tostring.ToStringIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private Long id;
    private String email;

    @ToStringIgnore
    private String password;

    private String name;
    private String phoneNumber;
    private String companyName;
    private String companyAddress;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
