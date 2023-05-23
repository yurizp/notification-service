package br.com.vibbra.notificationservice.db.entity.notification.email;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import br.com.vibbra.notificationservice.db.entity.AppEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@Entity(name = "email")
@NoArgsConstructor
@AllArgsConstructor
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AppEntity app;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public ServerEntity server;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public SenderEntity sender;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "email_id", referencedColumnName = "id")
    public List<EmailTemplateEntity> emailTemplates;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
