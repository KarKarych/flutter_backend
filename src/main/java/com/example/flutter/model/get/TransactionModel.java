package com.example.flutter.model.get;

import com.example.flutter.entity.enumeration.OperationType;

import java.time.Instant;
import java.util.UUID;

public record TransactionModel(
        UUID id,
        Integer amount,
        OperationType type,
        String target,
        Instant createdAt
) {
}
