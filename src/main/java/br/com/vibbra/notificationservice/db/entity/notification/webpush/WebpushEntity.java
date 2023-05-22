package br.com.vibbra.notificationservice.db.entity.notification.webpush;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import br.com.vibbra.notificationservice.db.entity.AppEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "webpush")
public class WebpushEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AppEntity app;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SiteEntity site;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AllowNotificationEntity allowNotification;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private WelcomeNotificationEntity welcomeNotification;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
