package com.example.flutter.mapper;

import com.example.flutter.entity.*;
import com.example.flutter.entity.composite.ArticleCategoryId;
import com.example.flutter.entity.enumeration.ArticleCategoryType;
import com.example.flutter.model.get.ArticleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Value;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Mapper
public abstract class ArticleMapper {

    @Value("${minio.url}")
    protected String minioUrl;

    @Value("${minio.article-bucket}")
    protected String articleBucket;

    @Mapping(target = "pictureUrl", source = "pictureUrl", qualifiedByName = "pictureUrlToModel")
    @Mapping(target = "categories", source = "articleCategories", qualifiedByName = "categoriesToModel")
    public abstract ArticleModel toModel(Article article);

    @Named("pictureUrlToModel")
    String generatePictureUrlToModel(String pictureUrl) {
        return "%s/%s/%s".formatted(minioUrl, articleBucket, pictureUrl);
    }

    @Named("categoriesToModel")
    List<ArticleCategoryType> generateCategoriesToModel(Set<ArticleCategory> entityCategories) {
        return entityCategories.stream()
                .map(ArticleCategory::getId)
                .map(ArticleCategoryId::getCategory)
                .sorted(Comparator.comparing(ArticleCategoryType::getId))
                .toList();
    }
}
