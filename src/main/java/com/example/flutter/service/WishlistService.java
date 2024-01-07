package com.example.flutter.service;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.model.get.ProductModel;
import com.example.flutter.model.update.WishlistProductModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface WishlistService {

    List<ProductModel> getByUserId(@NotNull UUID userId);

    List<ProductModel> addToWishlist(@NotNull FlutterUser flutterUser, @Valid @NotNull WishlistProductModel request);

    List<ProductModel> removeFromWishlist(@NotNull FlutterUser flutterUser, @Valid @NotNull WishlistProductModel request);

    void clear(@NotNull UUID userId);
}
