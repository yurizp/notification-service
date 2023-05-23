package br.com.yurizp.notificationservice.mapper;

import br.com.yurizp.notificationservice.controller.request.user.CreateUserRequest;
import br.com.yurizp.notificationservice.db.entity.UserEntity;

public class UserEntityMapper {

    public static UserEntity create(CreateUserRequest createUserRequest) {
        return UserEntity.builder()
                .email(createUserRequest.getEmail())
                .password(createUserRequest.getPassword())
                .name(createUserRequest.getName())
                .companyAddress(createUserRequest.getCompanyAddress())
                .companyName(createUserRequest.getCompanyName())
                .phoneNumber(createUserRequest.getPhoneNumber())
                .build();
    }
}
