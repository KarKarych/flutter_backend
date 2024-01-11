package com.example.flutter.service.impl;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.mapper.UserMapper;
import com.example.flutter.model.get.UserModel;
import com.example.flutter.model.update.UserUpdateModel;
import com.example.flutter.repository.UserRepository;
import com.example.flutter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.example.flutter.util.exception.BadRequestException.Code.EMAIL_ALREADY_EXISTS;
import static com.example.flutter.util.exception.BadRequestException.Code.LOGIN_ALREADY_EXISTS;
import static com.example.flutter.util.exception.NotFoundException.Code.USER_NOT_FOUND;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserModel getByPrincipal(FlutterUser currentUser) {
        return userMapper.toModel(currentUser);
    }

    @Override
    public UserModel getById(UUID userId) {
        return userRepository.findById(userId)
                .map(userMapper::toModel)
                .orElseThrow(() -> USER_NOT_FOUND.get("id = " + userId));
    }

    @Override
    @Transactional
    public UserModel update(FlutterUser currentUser, UserUpdateModel request) {
        var login = request.login();
        if (login != null && !login.equals(currentUser.getLogin())) {
            if (userRepository.existsByLogin(login)) {
                throw LOGIN_ALREADY_EXISTS.get(currentUser.getLogin(), "login = %s".formatted(login));
            }
            currentUser.setLogin(login);
        }

        var email = request.email();
        if (email != null && !email.equals(currentUser.getEmail())) {
            if (userRepository.existsByEmail(email)) {
                throw EMAIL_ALREADY_EXISTS.get(currentUser.getLogin(), "email = %s".formatted(email));
            }
            currentUser.setEmail(email);
        }

        var password = request.password();
        if (password != null) {
            currentUser.setPasswordHash(passwordEncoder.encode(password));
        }

        userMapper.merge(request, currentUser);
        var userPersisted = userRepository.save(currentUser);
        return userMapper.toModel(userPersisted);
    }
}
