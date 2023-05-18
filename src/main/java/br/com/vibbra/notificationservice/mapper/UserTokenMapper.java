package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.dto.User;
import br.com.vibbra.notificationservice.dto.UserToken;
import io.jsonwebtoken.Claims;

class UserTokenMapper {
    public static UserToken create(User user) {
        return UserToken.builder().id(user.getId()).email(user.getEmail()).build();
    }

    public static UserToken create(Claims claims) {
        return UserToken.builder()
                .id(Long.valueOf(String.valueOf(claims.get("userId"))))
                .email(String.valueOf(claims.get("userEmail")))
                .build();
    }
}
