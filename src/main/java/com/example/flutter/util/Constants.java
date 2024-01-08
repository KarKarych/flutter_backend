package com.example.flutter.util;

import com.example.flutter.model.get.ProductModel;
import lombok.experimental.UtilityClass;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class Constants {

    public final static String AUTHORIZATION_HEADER_START = "Bearer ";

    public final static String SEARCH_PATTERN = "%%%s%%";

    public final static Comparator<ProductModel> PRODUCT_COMPARATOR = Comparator
            .comparing(ProductModel::pictureUrlsSize).reversed()
            .thenComparing(ProductModel::description);

    private final static String[] BANKS = {
            "Сбер", "Тинькофф Банк", "ВТБ", "Альфа-Банк",
            "Райффайзен Банк", "Газпромбанк", "Банк ДОМ.РФ"
    };

    public static String getRandomBank() {
        return BANKS[ThreadLocalRandom.current().nextInt(0, BANKS.length)];
    }

    public final static String PAYMENT_TARGET = "Баланс кошелька";
}
