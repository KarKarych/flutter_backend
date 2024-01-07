package com.example.flutter.model.create;

import jakarta.validation.constraints.NotNull;

public record AuthCreateModel(
        @NotNull
        String login,
        @NotNull
        String password
) {
}