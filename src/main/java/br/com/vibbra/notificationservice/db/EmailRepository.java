package br.com.vibbra.notificationservice.db;

import br.com.vibbra.notificationservice.db.entity.notification.email.EmailEntity;
import br.com.vibbra.notificationservice.db.entity.notification.webpush.WebpushEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends CrudRepository<EmailEntity, Long> {

    Optional<EmailEntity> findByAppId(final Long appId);

    boolean existsByAppId(Long appId);
}
