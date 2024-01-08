package com.example.flutter.model.filter;

import jakarta.annotation.Nullable;

public record ProductFilter(
        @Nullable String searchQuery
) {
}
