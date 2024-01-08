package com.example.flutter.entity.enumeration;

import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
public enum OrderType {

    PENDING(1, "Обрабатывается"),
    PACKAGED(2, "Упакован"),
    SHIPPED(3, "Отправлен"),
    RECEIVED(4, "Получен");

    private final Integer id;
    private final String displayMessage;

    OrderType(Integer id, String displayMessage) {
        this.id = id;
        this.displayMessage = displayMessage;
    }

    public Short getId() {
        return id.shortValue();
    }

    public static OrderType getValueFromId(Short value) {
        if (value == null) {
            return null;
        }
        for (final OrderType type : OrderType.values()) {
            if (value.equals(type.getId())) {
                return type;
            }
        }
        return null;
    }

    public static OrderType getRandom() {
        int randomStatus = ThreadLocalRandom.current().nextInt(0, values().length);
        return values()[randomStatus];
    }
}
