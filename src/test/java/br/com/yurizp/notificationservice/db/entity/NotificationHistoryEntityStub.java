package br.com.yurizp.notificationservice.db.entity;

import br.com.yurizp.notificationservice.enums.Channel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotificationHistoryEntityStub {

    public static NotificationHistoryEntity create() {
        return NotificationHistoryEntity.builder()
                .id(new Random().nextLong())
                .app(AppEntityStub.create())
                .createdAt(LocalDate.now())
                .channel(Channel.SMS)
                .build();
    }

    public static List<NotificationHistoryEntity> createList() {
        return new ArrayList() {
            {
                add(create());
                add(create());
                add(create());
            }
        };
    }
}
