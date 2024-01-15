package com.example.flutter.model.get;

import com.example.flutter.entity.enumeration.SizeType;

import java.time.Instant;

public record OrderProductModel(
        ProductModel product,
        SizeType size,
        Integer amount,
        Instant createdAt
) {
}
