package com.example.flutter.model.get;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderModel(
        UUID id,
        List<ProductModel> products,
        Integer amount,
        Instant createdAt,
        String status
) {
}
