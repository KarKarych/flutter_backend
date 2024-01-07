package com.example.flutter.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SizeType {

    EU39(1, "EU 39"),
    EU40(2, "EU 40"),
    EU41(3, "EU 41"),
    EU42(4, "EU 42"),
    EU43(5, "EU 43"),
    EU44(6, "EU 44");

    private final Integer id;
    private final String displayMessage;

    SizeType(Integer id, String displayMessage) {
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

    public static SizeType getValueFromId(Short value) {
        if (value == null) {
            return null;
        }
        for (final SizeType type : SizeType.values()) {
            if (value.equals(type.getId())) {
                return type;
            }
        }
        return null;
    }

    public static SizeType getValueFromId(Integer value) {
        if (value == null) {
            return null;
        }
        for (final SizeType type : SizeType.values()) {
            if (value.equals(type.id)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
