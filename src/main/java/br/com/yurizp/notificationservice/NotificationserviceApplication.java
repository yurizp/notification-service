package br.com.yurizp.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.vibbra", "br.com.yurizp"})
public class NotificationserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationserviceApplication.class, args);
    }
}