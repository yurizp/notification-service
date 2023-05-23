package br.com.yurizp.notificationservice.db;

import br.com.yurizp.notificationservice.db.entity.NotificationHistoryEntity;
import br.com.yurizp.notificationservice.enums.Channel;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationHistoryRepository extends CrudRepository<NotificationHistoryEntity, Long> {

    List<NotificationHistoryEntity> findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqualAndAppIdAndChannel(
            LocalDate startDate, LocalDate endDate, Long appId, Channel channel);
}
