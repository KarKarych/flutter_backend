package com.example.flutter.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.example.flutter.repository.base.ExtendedRepositoryImpl;

@EnableJpaAuditing
@EntityScan({"com.example.flutter.entity", "com.example.flutter.converter"})
@EnableJpaRepositories(
        basePackages = "com.example.flutter.repository",
        repositoryBaseClass = ExtendedRepositoryImpl.class
)
@Configuration
public class DatabaseConfiguration {
}
