package br.com.vibbra.notificationservice.dto;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import br.com.vibbra.notificationservice.config.tostring.ToStringIgnore;
import java.util.Date;
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
public class JwtToken {

    @ToStringIgnore
    private String token;

    private UserToken user;
    private Date expiration;
    private Date issuedAt;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
