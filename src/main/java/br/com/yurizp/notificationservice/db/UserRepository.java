package br.com.yurizp.notificationservice.db;

import br.com.yurizp.notificationservice.db.entity.UserEntity;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    boolean existsByEmail(final String email);

    Optional<UserEntity> findByEmailEqualsAndPasswordEquals(final String email, final String password);
}
