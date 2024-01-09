package com.example.flutter.util;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Collections;

@UtilityClass
public class CollectionUtils {

    public static <T> Collection<T> emptyIfNull(final Collection<T> collection) {
        return collection == null ? Collections.emptyList() : collection;
    }
}
