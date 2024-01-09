package com.example.flutter.configuration.security;

import com.example.flutter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.UUID;

@RequiredArgsConstructor
public class BerarerAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        UUID userId = (UUID) token.getPrincipal();
        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User with given userId = %s not found".formatted(userId)));
    }
}
