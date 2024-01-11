package com.example.flutter.service.impl;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.model.create.AuthCreateModel;
import com.example.flutter.model.get.AuthModel;
import com.example.flutter.repository.UserRepository;
import com.example.flutter.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.flutter.util.Constants.AUTHORIZATION_HEADER_START;
import static com.example.flutter.util.exception.BadRequestException.Code.PASSWORDS_DO_NOT_MATCH;
import static com.example.flutter.util.exception.NotFoundException.Code.USER_NOT_FOUND;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthModel login(AuthCreateModel request) {
        FlutterUser user = userRepository.findByLogin(request.login())
                .orElseThrow(() -> USER_NOT_FOUND.get("login = %s".formatted(request.login())));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw PASSWORDS_DO_NOT_MATCH.get(request.login());
        }

        return new AuthModel(AUTHORIZATION_HEADER_START + user.getId());
    }
}
