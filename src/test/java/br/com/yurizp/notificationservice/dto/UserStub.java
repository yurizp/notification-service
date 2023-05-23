package br.com.yurizp.notificationservice.dto;

public class UserStub {

    public static User create() {
        return User.builder()
                .id(123L)
                .email("valid@email.com")
                .password("password")
                .name("name")
                .phoneNumber("phoneNumber")
                .companyAddress("company address")
                .companyName("company name")
                .build();
    }

    public static User createWithJustRequeridFields() {
        return User.builder()
                .id(123L)
                .email("valid@email.com")
                .password("password")
                .name("name")
                .build();
    }
}
