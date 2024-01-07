package com.example.flutter.util;

import com.example.flutter.model.get.ProductModel;
import lombok.experimental.UtilityClass;

import java.util.Comparator;

@UtilityClass
public class Constants {

    public final static String AUTHORIZATION_HEADER_START = "Bearer ";

    public final static String SEARCH_PATTERN = "%%%s%%";

    public final static Comparator<ProductModel> PRODUCT_COMPARATOR = Comparator
            .comparing(ProductModel::pictureUrlsSize).reversed()
            .thenComparing(ProductModel::description);
}
