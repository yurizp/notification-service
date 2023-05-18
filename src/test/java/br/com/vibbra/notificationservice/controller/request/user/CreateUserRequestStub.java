package br.com.vibbra.notificationservice.controller.request.user;

public class CreateUserRequestStub {

    public static CreateUserRequest create() {
        return CreateUserRequest.builder()
                .email("valid@email.com")
                .password("password")
                .name("test silva")
                .phoneNumber("5551984908883")
                .companyName("company name")
                .companyAddress("company address")
                .build();
    }

    public static CreateUserRequest createWithJustRequeridFields() {
        return CreateUserRequest.builder()
                .email("valid@email.com")
                .password("password")
                .name("test silva")
                .build();
    }
}
