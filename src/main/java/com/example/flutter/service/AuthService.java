package com.example.flutter.service;

import com.example.flutter.model.create.AuthCreateModel;
import com.example.flutter.model.get.AuthModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface AuthService {


    AuthModel login(@Valid @NotNull AuthCreateModel request);
}
