package com.example.flutter.converter;

import com.example.flutter.entity.enumeration.ProductCategoryType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ProductTypeConverter implements AttributeConverter<ProductCategoryType, Short> {

    @Override
    public Short convertToDatabaseColumn(ProductCategoryType attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException("Can't construct database column");
        }
        return attribute.getId();
    }

    @Override
    public ProductCategoryType convertToEntityAttribute(Short dbData) {
        ProductCategoryType valueFromId = ProductCategoryType.getValueFromId(dbData);
        if (valueFromId == null) {
            throw new IllegalArgumentException("Can't construct java object from database column");
        }
        return valueFromId;
    }
}