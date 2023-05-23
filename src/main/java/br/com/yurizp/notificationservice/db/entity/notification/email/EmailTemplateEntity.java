package br.com.yurizp.notificationservice.db.entity.notification.email;

import br.com.yurizp.commons.tostring.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@Entity(name = "email_template")
@NoArgsConstructor
@AllArgsConstructor
public class EmailTemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String uri;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
