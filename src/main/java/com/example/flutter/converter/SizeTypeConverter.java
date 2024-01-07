package com.example.flutter.converter;

import com.example.flutter.entity.enumeration.SizeType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SizeTypeConverter implements AttributeConverter<SizeType, Short> {

    @Override
    public Short convertToDatabaseColumn(SizeType attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException("Can't construct database column");
        }
        return attribute.getId();
    }

    @Override
    public SizeType convertToEntityAttribute(Short dbData) {
        SizeType valueFromId = SizeType.getValueFromId(dbData);
        if (valueFromId == null) {
            throw new IllegalArgumentException("Can't construct java object from database column");
        }
        return valueFromId;
    }
}