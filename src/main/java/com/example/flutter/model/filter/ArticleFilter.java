package com.example.flutter.model.filter;

import jakarta.annotation.Nullable;

public record ArticleFilter(
        @Nullable String searchQuery
) {
}
