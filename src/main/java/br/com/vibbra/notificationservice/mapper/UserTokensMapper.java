package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.dto.User;
import br.com.vibbra.notificationservice.dto.UserToken;
import io.jsonwebtoken.Claims;

public class UserTokensMapper {
    public static UserToken create(User user) {
        return UserToken.builder()
                .userName(user.getUsername())
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    public static UserToken create(Claims claims) {
        return UserToken.builder()
                .id(String.valueOf(claims.get("userId")))
                .email(String.valueOf(claims.get("userEmail")))
                .userName(String.valueOf(claims.get("userName")))
                .build();
    }
}
