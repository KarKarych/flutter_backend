package com.example.flutter.service;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.model.get.UserModel;
import com.example.flutter.model.update.UserUpdateModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public interface UserService {

    UserModel getByPrincipal(@NotNull FlutterUser currentUser);

    UserModel getById(@NotNull UUID userId);

    UserModel update(
            @NotNull FlutterUser currentUser,
            @Valid @NotNull UserUpdateModel request
    );
}
