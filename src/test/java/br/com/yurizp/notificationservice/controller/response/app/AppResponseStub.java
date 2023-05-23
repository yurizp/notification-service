package br.com.yurizp.notificationservice.controller.response.app;

public class AppResponseStub {

    public static AppResponse create() {
        return AppResponse.builder()
                .appId(123L)
                .appName("app name")
                .appToken("token")
                .build();
    }
}
