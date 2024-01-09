package com.example.flutter.model.get;

import com.example.flutter.entity.enumeration.ProductCategoryType;
import com.example.flutter.entity.enumeration.SizeType;

import java.util.List;
import java.util.UUID;

public record ProductModel(
        UUID id,
        String name,
        String description,
        List<String> pictureUrls,
        Integer price,
        Integer ratingPercent,
        Integer ratingNumber,
        ProductCategoryType category,
        List<SizeType> sizes
) {
}
