package br.com.vibbra.notificationservice.db.entity.notification.email;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Entity(name = "server")
@NoArgsConstructor
@AllArgsConstructor
public class ServerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("smtp_name")
    private String smtpName;
    @JsonProperty("smpt_port")
    private String smptPort;
    @JsonProperty("user_login")
    private String userLogin;
    @JsonProperty("user_password")
    private String userPassword;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
