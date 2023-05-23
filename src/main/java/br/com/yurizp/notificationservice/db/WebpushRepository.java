package br.com.yurizp.notificationservice.db;

import br.com.yurizp.notificationservice.db.entity.notification.webpush.WebpushEntity;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebpushRepository extends CrudRepository<WebpushEntity, Long> {

    Optional<WebpushEntity> findByAppId(final Long appId);

    boolean existsByAppId(Long appId);
}
