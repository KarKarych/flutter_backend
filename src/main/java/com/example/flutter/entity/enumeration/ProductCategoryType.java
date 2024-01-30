package com.example.flutter.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ProductCategoryType {

    RUNNING(1, "Кроссовки для бега"),
    FOOTBALL(2, "Кроссовки для футбола"),
    OUTDOOR_EXPLORER(3, "Обувь для активного отдыха"),
    CROSS_TRAINING(4, "Обувь для кроссовых тренировок"),
    MARATHON(5, "Спортивная обувь и тренажеры для зала"),
    ATHLETIC_AND_GYM(6, "Обувь для марафона"),
    ;

    private final Integer id;
    private final String displayMessage;

    ProductCategoryType(Integer id, String displayMessage) {
        this.id = id;
        this.displayMessage = displayMessage;
    }

    public static ProductCategoryType getValueFromId(Short value) {
        if (value == null) {
            return null;
        }
        for (final ProductCategoryType type : ProductCategoryType.values()) {
            if (value.equals(type.getId())) {
                return type;
            }
        }
        return null;
    }

    @JsonValue
    public Short getId() {
        return id.shortValue();
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
