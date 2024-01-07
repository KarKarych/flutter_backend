package com.example.flutter.model.get;

import com.example.flutter.entity.enumeration.ArticleCategoryType;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ArticleModel(
        UUID id,
        String name,
        String text,
        String pictureUrl,
        Instant createdAt,
        List<ArticleCategoryType> categories
) {
}
