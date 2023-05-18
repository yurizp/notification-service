package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.dto.JwtToken;
import br.com.vibbra.notificationservice.dto.User;
import io.jsonwebtoken.Claims;
import java.util.Date;

public class JwtTokenMapper {
    public static JwtToken create(final String token, final User user, final Date expiration, final Date issuedAt) {
        return JwtToken.builder()
                .token(token)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .user(UserTokenMapper.create(user))
                .build();
    }

    public static JwtToken create(final Claims claims, final String token) {
        return JwtToken.builder()
                .token(token)
                .issuedAt(claims.getIssuedAt())
                .expiration(claims.getExpiration())
                .user(UserTokenMapper.create(claims))
                .build();
    }
}
