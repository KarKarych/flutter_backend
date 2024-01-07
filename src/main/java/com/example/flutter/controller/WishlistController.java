package com.example.flutter.controller;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.model.basic.ContentModel;
import com.example.flutter.model.get.ProductModel;
import com.example.flutter.model.update.WishlistProductModel;
import com.example.flutter.service.WishlistService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearer")
@RequiredArgsConstructor
@RequestMapping("/wishlists")
@RestController
public class WishlistController {

    private final WishlistService service;

    @GetMapping("/products")
    public ContentModel<List<ProductModel>> getByUserId(
            @AuthenticationPrincipal FlutterUser currentUser
    ) {
        var response = service.getByUserId(currentUser.getId());
        return ContentModel.okContentModel(response);
    }

    @PutMapping("/products")
    public ContentModel<List<ProductModel>> addToWishlist(
            @AuthenticationPrincipal FlutterUser currentUser,
            @RequestBody WishlistProductModel request
    ) {
        var response = service.addToWishlist(currentUser, request);
        return ContentModel.okContentModel(response);
    }

    @DeleteMapping("/products")
    public ContentModel<List<ProductModel>> removeFromWishlist(
            @AuthenticationPrincipal FlutterUser currentUser,
            @RequestBody WishlistProductModel request
    ) {
        var response = service.removeFromWishlist(currentUser, request);
        return ContentModel.okContentModel(response);
    }

    @DeleteMapping("/clear")
    public ContentModel<String> clear(
            @AuthenticationPrincipal FlutterUser currentUser
    ) {
        service.clear(currentUser.getId());
        return ContentModel.deletedContentModel("The wishlist is cleared");
    }
}

