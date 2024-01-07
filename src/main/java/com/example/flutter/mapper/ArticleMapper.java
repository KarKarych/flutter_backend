package com.example.flutter.mapper;

import com.example.flutter.entity.*;
import com.example.flutter.entity.composite.ArticleCategoryId;
import com.example.flutter.entity.enumeration.ArticleCategoryType;
import com.example.flutter.model.get.ArticleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper
public interface ArticleMapper {

    @Mapping(target = "categories", source = "articleCategories", qualifiedByName = "categoriesToModel")
    ArticleModel toModel(Article article);

    @Named("categoriesToModel")
    default List<ArticleCategoryType> generateCategoriesToModel(Set<ArticleCategory> entityCategories) {
        return entityCategories.stream()
                .map(ArticleCategory::getId)
                .map(ArticleCategoryId::getCategory)
                .toList();
    }
}
