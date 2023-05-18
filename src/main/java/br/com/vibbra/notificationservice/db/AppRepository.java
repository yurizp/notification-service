package br.com.vibbra.notificationservice.db;

import br.com.vibbra.notificationservice.db.entity.AppEntity;
import br.com.vibbra.notificationservice.db.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppRepository extends CrudRepository<AppEntity, Long> {

    Optional<AppEntity> findByIdAndUserId(final Long id, final Long userId);
}
