package com.example.flutter.model.create;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransactionCreateModel(
        @NotNull @Positive Integer amount,
        @Nullable String target
) {

    public static TransactionCreateModel of(Integer amount) {
        return new TransactionCreateModel(amount, null);
    }
}
