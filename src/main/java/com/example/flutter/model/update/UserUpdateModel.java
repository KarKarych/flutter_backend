package com.example.flutter.model.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UserUpdateModel(
        String login,
        @Email String email,
        String lastName,
        String firstName,
        String password,
        @Pattern(regexp = "(\\+7|8)[0-9]{10}") String phoneNumber,
        String address
) {
}
