package br.com.vibbra.notificationservice.db;

import br.com.vibbra.notificationservice.db.entity.notification.email.EmailEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends CrudRepository<EmailEntity, Long> {

    Optional<EmailEntity> findByAppId(final Long appId);

    boolean existsByAppId(Long appId);
}
