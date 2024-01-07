package com.example.flutter.configuration;

import com.example.flutter.repository.UserRepository;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Configuration
public class OpenAPIConfig {

    private final UserRepository userRepository;

    @Value("${server-url}")
    private String serverUrl;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(getInfo())
                .servers(getServer())
                .components(getComponents());
    }

    private Info getInfo() {
        return new Info()
                .title("Sport API")
                .description(getDescription())
                .version("1.0");
    }

    private String getDescription() {
        var builder = new StringBuilder("UUID является Bearer токеном\n");
        var users = userRepository.findByOrderByLastNameDesc();
        IntStream.range(0, users.size())
                .mapToObj(i -> String.format("%d. %s\n", i + 1, users.get(i).getId()))
                .forEach(builder::append);
        return builder.toString();
    }

    private List<Server> getServer() {
        Server server = new Server();
        server.setUrl(serverUrl);
        server.setDescription("Server URL");
        return List.of(server);
    }

    private Components getComponents() {
        return new Components()
                .addSecuritySchemes(
                        "bearer",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                );
    }
}
