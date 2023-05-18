package br.com.vibbra.notificationservice.mapper;

import br.com.vibbra.notificationservice.dto.User;
import java.util.HashMap;
import java.util.Map;

public class ClaimsMapper {
    public static Map<String, Object> create(User user) {
        Map<String, Object> userClaims = new HashMap<>();
        userClaims.put("userId", user.getId());
        userClaims.put("userEmail", user.getEmail());
        userClaims.put("userName", user.getUsername());
        return userClaims;
    }
}
