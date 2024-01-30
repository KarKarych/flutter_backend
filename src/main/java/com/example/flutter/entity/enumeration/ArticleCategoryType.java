package com.example.flutter.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ArticleCategoryType {

    BASKETBALL(1, "Баскетбол"),
    CYCLING_ADVENTURES(2, "Велосипедные приключения"),
    SCIENCE_AND_SPORTS(3, "Наука и спорт"),
    FITNESS_AND_WELLNESS(4, "Фитнес и велнес"),
    EXTREME_SPORTS(5, "Экстремальные виды спорта"),
    RACQUET_SPORTS(6, "Ракетки"),
    GOLF_(7, "Гольф"),
    RUNNING(8, "Бегущий"),
    ARCHERY(9, "Стрельба из лука"),
    ROCK_CLIMBING(10, "Скалолазание"),
    EQUESTRIAN_SPORTS(11, "Конный спорт"),
    MARTIAL_ARTS(12, "Боевые искусства"),
    SURFING(13, "Серфинг"),
    BASKETBALL_BASICS(14, "Основы баскетбола"),
    YOGA_FOR_ATHLETES(15, "Йога для спортсменов"),
    FOOTBALL(16, "Футбол"),
    WINTER_SPORTS(17, "Зимние виды спорта"),
    MOUNTAIN_CLIMBING(18, "Альпинизм"),
    TABLE_TENNIS(19, "Настольный теннис"),
    ;

    private final Integer id;
    private final String displayMessage;

    ArticleCategoryType(Integer id, String displayMessage) {
        this.id = id;
        this.displayMessage = displayMessage;
    }

    public static ArticleCategoryType getValueFromId(Short value) {
        if (value == null) {
            return null;
        }
        for (final ArticleCategoryType type : ArticleCategoryType.values()) {
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
