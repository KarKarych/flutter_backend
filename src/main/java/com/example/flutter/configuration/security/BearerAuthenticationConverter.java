package com.example.flutter.configuration.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.UUID;

import static com.example.flutter.util.Constants.AUTHORIZATION_HEADER_START;
import static java.util.UUID.fromString;

@Slf4j
@RequiredArgsConstructor
public class BearerAuthenticationConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest request) {
        var authentication = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authentication == null || !authentication.startsWith(AUTHORIZATION_HEADER_START)) {
            return null;
        }

        var rawToken = authentication.substring(AUTHORIZATION_HEADER_START.length());

        if (getPrincipal(rawToken) == null) {
            return null;
        }

        return new PreAuthenticatedAuthenticationToken(rawToken, "Лапы и хвост -- мои документы");
    }

    private static UUID getPrincipal(String rawToken) {
        try {
            return fromString(rawToken);
        } catch (IllegalArgumentException ex) {
            log.debug("Bad UUID in token {}", rawToken);
            return null;
        }
    }
}