package br.com.yurizp.notificationservice.db;

import br.com.yurizp.notificationservice.db.entity.NotificationConfigEntity;
import br.com.yurizp.notificationservice.enums.Channel;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationConfigRepository extends CrudRepository<NotificationConfigEntity, Long> {

    Optional<NotificationConfigEntity> findByAppIdAndChannel(final Long appId, Channel channel);
}
