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
@Entity(name = "welcome_notification")
public class WelcomeNotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String messageTitle;
    private String messageText;
    private boolean enableUrlRedirect;
    private String urlRedirect;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
