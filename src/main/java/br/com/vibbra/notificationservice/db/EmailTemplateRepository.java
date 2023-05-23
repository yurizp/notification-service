package br.com.vibbra.notificationservice.db;

import br.com.vibbra.notificationservice.db.entity.notification.email.EmailTemplateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTemplateRepository extends CrudRepository<EmailTemplateEntity, Long> {}
