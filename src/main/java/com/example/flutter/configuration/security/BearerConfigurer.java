package com.example.flutter.configuration.security;

import com.example.flutter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
public class BearerConfigurer extends AbstractHttpConfigurer<BearerConfigurer, HttpSecurity> {

    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final UserRepository userRepository;

    @Override
    public void init(HttpSecurity builder) throws Exception {
        builder.exceptionHandling(c -> c.authenticationEntryPoint(authenticationEntryPoint));
    }

    @Override
    public void configure(HttpSecurity builder) {
        var authenticationManager = builder.getSharedObject(AuthenticationManager.class);

        var authenticationFilter = new AuthenticationFilter(authenticationManager, new BearerAuthenticationConverter());
        authenticationFilter.setSuccessHandler((request, response, authentication) -> {});
        authenticationFilter.setFailureHandler(new AuthenticationEntryPointFailureHandler(authenticationEntryPoint));

        var authenticationProvider = new PreAuthenticatedAuthenticationProvider();
        authenticationProvider.setPreAuthenticatedUserDetailsService(new BerarerAuthenticationUserDetailsService(userRepository));

        builder.addFilterBefore(authenticationFilter, BasicAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider);
    }
}
