package com.example.appnews;

import com.example.appnews.component.DataLoader;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootApplication
public class AppNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppNewsApplication.class, args);
    }

}
