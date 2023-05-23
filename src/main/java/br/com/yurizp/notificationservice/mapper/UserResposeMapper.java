package br.com.yurizp.notificationservice.mapper;

import br.com.yurizp.notificationservice.controller.response.UserResponse;
import br.com.yurizp.notificationservice.dto.User;

public class UserResposeMapper {

    public static UserResponse create(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .companyName(user.getCompanyName())
                .companyAddress(user.getCompanyAddress())
                .build();
    }
}
