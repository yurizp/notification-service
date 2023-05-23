package br.com.vibbra.notificationservice.db;

import br.com.vibbra.notificationservice.db.entity.notification.sms.SmsEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends CrudRepository<SmsEntity, Long> {

    Optional<SmsEntity> findByAppId(final Long appId);

    boolean existsByAppId(Long appId);
}
