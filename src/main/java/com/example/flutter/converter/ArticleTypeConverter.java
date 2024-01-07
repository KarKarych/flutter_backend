package com.example.flutter.converter;

import com.example.flutter.entity.enumeration.ArticleCategoryType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ArticleTypeConverter implements AttributeConverter<ArticleCategoryType, Short> {

    @Override
    public Short convertToDatabaseColumn(ArticleCategoryType attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException("Can't construct database column");
        }
        return attribute.getId();
    }

    @Override
    public ArticleCategoryType convertToEntityAttribute(Short dbData) {
        ArticleCategoryType valueFromId = ArticleCategoryType.getValueFromId(dbData);
        if (valueFromId == null) {
            throw new IllegalArgumentException("Can't construct java object from database column");
        }
        return valueFromId;
    }
}