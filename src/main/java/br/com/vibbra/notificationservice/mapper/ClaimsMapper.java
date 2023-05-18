package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.dto.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ClaimsMapper {
    public static Map<String, Object> create(User user) {
        Map<String, Object> userClaims = new HashMap<>();
        if (Objects.isNull(user)) return userClaims;
        userClaims.put("userId", user.getId());
        userClaims.put("userEmail", user.getEmail());
        return userClaims;
    }
}
