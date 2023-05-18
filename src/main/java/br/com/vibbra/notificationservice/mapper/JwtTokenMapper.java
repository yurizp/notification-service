package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.dto.JwtToken;
import br.com.vibbra.notificationservice.dto.User;
import io.jsonwebtoken.Claims;

public class JwtTokenMapper {
    public static JwtToken create(final String token, final User user) {
        return JwtToken.builder()
                .token(token)
                .user(UserTokensMapper.create(user))
                .build();
    }

    public static JwtToken create(final Claims claims, final String token) {
        return JwtToken.builder()
                .token(token)
                .user(UserTokensMapper.create(claims))
                .build();
    }
}
