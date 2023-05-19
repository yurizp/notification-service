package br.com.vibbra.notificationservice.db;

import br.com.vibbra.notificationservice.db.entity.AppEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends CrudRepository<AppEntity, Long> {

    Optional<AppEntity> findByIdAndUserId(final Long id, final Long userId);

    boolean existsByIdAndUserId(final Long id, final Long userId);
}
