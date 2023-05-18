package br.com.vibbra.notificationservice.db.entity;

public class AppEntityStub {

    public static AppEntity create() {
        return AppEntity.builder()
                .id(123L)
                .name("name")
                .token("token")
                .user(UserEntityStub.create())
                .build();
    }
}