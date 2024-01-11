package com.example.flutter.model.get;

import com.example.flutter.entity.enumeration.SizeType;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record OrderProductModel(
        ProductModel product,
        SizeType size,
        Integer amount
) {

    @JsonIgnore
    public String productName() {
        return product.name();
    }
}
