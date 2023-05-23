package br.com.vibbra.notificationservice.db.entity.notification.email;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity(name = "sender")
@NoArgsConstructor
@AllArgsConstructor
public class SenderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
