package com.example.flutter.controller;

import com.example.flutter.entity.enumeration.ArticleCategoryType;
import com.example.flutter.entity.enumeration.ProductCategoryType;
import com.example.flutter.model.basic.ContentModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@SecurityRequirement(name = "bearer")
@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoryController {

    @GetMapping("/products")
    public ContentModel<Map<Short, String>> getAllProductCategories() {
        var response = Arrays.stream(ProductCategoryType.values())
                .collect(toMap(ProductCategoryType::getId, ProductCategoryType::getDisplayMessage));
        return ContentModel.okContentModel(response);
    }

    @GetMapping("/articles")
    public ContentModel<Map<Short, String>> getAllArticleCategories() {
        var response = Arrays.stream(ArticleCategoryType.values())
                .collect(toMap(ArticleCategoryType::getId, ArticleCategoryType::getDisplayMessage));
        return ContentModel.okContentModel(response);
    }
}

