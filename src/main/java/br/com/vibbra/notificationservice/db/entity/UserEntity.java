package br.com.vibbra.notificationservice.db.entity;

import br.com.vibbra.notificationservice.config.tostring.Objects;
import br.com.vibbra.notificationservice.config.tostring.ToStringIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(
        name = "user",
        indexes = {@Index(name = "email_index", columnList = "email", unique = true)})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @ToStringIgnore
    private String password;

    private String name;
    private String phoneNumber;
    private String companyName;
    private String companyAddress;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
