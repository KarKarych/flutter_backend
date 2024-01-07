package com.example.flutter.service;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.model.get.ProductModel;
import com.example.flutter.model.update.BucketProductModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface BucketService {

    List<ProductModel> getByUserId(@NotNull UUID userId);

    List<ProductModel> addToBucket(@NotNull FlutterUser flutterUser, @Valid @NotNull BucketProductModel request);

    List<ProductModel> removeFromBucket(@NotNull FlutterUser flutterUser, @Valid @NotNull BucketProductModel request);

    void clear(@NotNull UUID userId);
}
