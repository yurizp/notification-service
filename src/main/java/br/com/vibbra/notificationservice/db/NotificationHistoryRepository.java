package br.com.vibbra.notificationservice.db;

import br.com.vibbra.notificationservice.db.entity.NotificationHistoryEntity;
import br.com.vibbra.notificationservice.enums.Channel;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationHistoryRepository extends CrudRepository<NotificationHistoryEntity, Long> {

    List<NotificationHistoryEntity> findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqualAndAppIdAndChannel(
            LocalDate startDate, LocalDate endDate, Long appId, Channel channel);
}
