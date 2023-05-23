package br.com.yurizp.notificationservice.db.entity;

import br.com.yurizp.commons.tostring.Objects;
import br.com.yurizp.notificationservice.enums.Channel;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "notification_config",
        indexes = {@Index(name = "notification_channel_app_index", columnList = "channel, app_id", unique = true)})
public class NotificationConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AppEntity app;

    @Enumerated(EnumType.STRING)
    private Channel channel;

    private boolean enabled;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
