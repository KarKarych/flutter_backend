package com.example.flutter.converter;

import com.example.flutter.entity.enumeration.OperationType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OperationTypeConverter implements AttributeConverter<OperationType, Short> {

    @Override
    public Short convertToDatabaseColumn(OperationType attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException("Can't construct database column");
        }
        return attribute.getId();
    }

    @Override
    public OperationType convertToEntityAttribute(Short dbData) {
        OperationType valueFromId = OperationType.getValueFromId(dbData);
        if (valueFromId == null) {
            throw new IllegalArgumentException("Can't construct java object from database column");
        }
        return valueFromId;
    }
}