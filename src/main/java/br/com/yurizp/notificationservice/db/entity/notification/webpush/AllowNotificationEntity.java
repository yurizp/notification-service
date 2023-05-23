package br.com.yurizp.notificationservice.db.entity.notification.webpush;

import br.com.yurizp.commons.tostring.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "allow_notificatio")
public class AllowNotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String messageText;
    private String allowButtonText;
    private String denyButtonText;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
