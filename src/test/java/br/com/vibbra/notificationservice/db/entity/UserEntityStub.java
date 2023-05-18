package br.com.vibbra.notificationservice.db.entity;

public class UserEntityStub {

    public static UserEntity create() {
        return UserEntity.builder()
                .id(123L)
                .email("valid@email.com")
                .password("password")
                .name("name")
                .phoneNumber("phoneNumber")
                .companyAddress("company address")
                .companyName("company name")
                .build();
    }
}
