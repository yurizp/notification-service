package br.com.yurizp.notificationservice.controller.request.authentication;

public class AuthRequestStub {

    public static AuthRequest create() {
        return AuthRequest.builder()
                .email("valid@email.com")
                .password("password")
                .build();
    }
}
