package com.example.flutter.converter;

import com.example.flutter.entity.enumeration.OrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, Short> {

    @Override
    public Short convertToDatabaseColumn(OrderStatus attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException("Can't construct database column");
        }
        return attribute.getId();
    }

    @Override
    public OrderStatus convertToEntityAttribute(Short dbData) {
        OrderStatus valueFromId = OrderStatus.getValueFromId(dbData);
        if (valueFromId == null) {
            throw new IllegalArgumentException("Can't construct java object from database column");
        }
        return valueFromId;
    }
}