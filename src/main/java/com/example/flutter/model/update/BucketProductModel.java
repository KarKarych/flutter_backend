package com.example.flutter.model.update;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record BucketProductModel(

        @NotEmpty
        @NotNull
        List<UUID> productIds
) {
}
