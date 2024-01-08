package com.example.flutter.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum OperationType {

    TOP_UP(1, "Пополнение"),
    PAYMENT(2, "Оплата")
    ;

    private final Integer id;
    private final String displayMessage;

    OperationType(Integer id, String displayMessage) {
        this.id = id;
        this.displayMessage = displayMessage;
    }

    public Short getId() {
        return id.shortValue();
    }

    @JsonValue
    public String getDisplayMessage() {
        return displayMessage;
    }

    public static OperationType getValueFromId(Short value) {
        if (value == null) {
            return null;
        }
        for (final OperationType type : OperationType.values()) {
            if (value.equals(type.getId())) {
                return type;
            }
        }
        return null;
    }
}
