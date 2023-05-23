package br.com.vibbra.notificationservice.db;

import br.com.vibbra.notificationservice.db.entity.notification.email.EmailEntity;
import br.com.vibbra.notificationservice.db.entity.notification.sms.SmsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmsRepository extends CrudRepository<SmsEntity, Long> {

    Optional<SmsEntity> findByAppId(final Long appId);

    boolean existsByAppId(Long appId);
}
