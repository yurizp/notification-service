package br.com.vibbra.notificationservice.config;

import br.com.vibbra.notificationservice.enums.Channel;
import br.com.vibbra.notificationservice.strategy.NotificationStrategy;
import br.com.vibbra.notificationservice.strategy.ValidationNotificationStrategy;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

@Configuration
public class NotificationBeanConfig {

    @Bean
    public Map<Channel, NotificationStrategy> beanToNotificationStrategy(
            final List<NotificationStrategy> notifications) {
        return notifications.stream().collect(Collectors.toMap(NotificationStrategy::getChannel, n -> n));
    }

    @Bean
    public Map<Channel, ValidationNotificationStrategy> beanToValidationStrategy(
            final List<ValidationNotificationStrategy> validations) {
        if (CollectionUtils.isEmpty(validations)) {
            return Collections.EMPTY_MAP;
        }
        return validations.stream().collect(Collectors.toMap(ValidationNotificationStrategy::getChannel, v -> v));
    }
}
