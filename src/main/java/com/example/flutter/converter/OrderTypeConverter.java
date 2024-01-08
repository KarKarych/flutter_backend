package com.example.flutter.converter;

import com.example.flutter.entity.enumeration.OrderType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderTypeConverter implements AttributeConverter<OrderType, Short> {

    @Override
    public Short convertToDatabaseColumn(OrderType attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException("Can't construct database column");
        }
        return attribute.getId();
    }

    @Override
    public OrderType convertToEntityAttribute(Short dbData) {
        OrderType valueFromId = OrderType.getValueFromId(dbData);
        if (valueFromId == null) {
            throw new IllegalArgumentException("Can't construct java object from database column");
        }
        return valueFromId;
    }
}