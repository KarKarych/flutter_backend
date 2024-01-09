package com.example.flutter.model.filter;

import com.example.flutter.entity.enumeration.ProductCategoryType;
import com.example.flutter.util.CollectionUtils;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Objects;

public record ProductFilter(
        @Nullable String searchQuery,
        @Nullable List<ProductCategoryType> categories
) {

    public static ProductFilter of(String searchQuery, List<Short> categoryIds) {
        return new ProductFilter(
                searchQuery,
                CollectionUtils.emptyIfNull(categoryIds).stream()
                        .map(ProductCategoryType::getValueFromId)
                        .filter(Objects::nonNull)
                        .toList()
        );
    }

    @Override
    public String searchQuery() {
        return searchQuery == null ? null : searchQuery.strip();
    }
}
