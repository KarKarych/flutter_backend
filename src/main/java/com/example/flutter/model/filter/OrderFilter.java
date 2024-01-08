package com.example.flutter.model.filter;

import com.example.flutter.entity.enumeration.OrderType;
import jakarta.annotation.Nullable;

public record OrderFilter(
        @Nullable OrderType status
) {
}
