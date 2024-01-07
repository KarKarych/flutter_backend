package com.example.flutter.service.impl;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.mapper.UserMapper;
import com.example.flutter.model.get.UserModel;
import com.example.flutter.repository.UserRepository;
import com.example.flutter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.example.flutter.util.exception.NotFoundException.Code.USER_NOT_FOUND;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

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
}
