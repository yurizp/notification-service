package br.com.vibbra.notificationservice.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;
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

    private String id;
    private String userName;
    private String email;
    private Date expiration;
    private Date issuedAt;
    private List<String> role;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
