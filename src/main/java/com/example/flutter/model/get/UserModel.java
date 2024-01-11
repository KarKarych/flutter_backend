package com.example.flutter.model.get;

import java.util.UUID;

public record UserModel(
        UUID id,
        String login,
        String email,
        String lastName,
        String firstName,
        Integer balance,
        String phoneNumber,
        String address,
        String pictureUrl
) {
}
