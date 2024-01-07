package com.example.flutter.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    public static final String DESCRIPTION = """
            UUID является Bearer токеном
            1. 123e4567-e89b-12d3-a456-426614174000
            2. d5c33420-637e-458f-95db-8091f91c511a
            3. fa1b64dc-319c-4504-bc3e-e2e625487f47
            """;

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
                .description(DESCRIPTION)
                .version("1.0");
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
