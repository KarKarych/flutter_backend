package com.example.flutter.controller;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.model.basic.ContentModel;
import com.example.flutter.model.get.OrderProductModel;
import com.example.flutter.model.update.BucketProductModel;
import com.example.flutter.service.BucketService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearer")
@RequiredArgsConstructor
@RequestMapping("/buckets")
@RestController
public class BucketController {

    private final BucketService service;

    @GetMapping("/products")
    public ContentModel<List<OrderProductModel>> getByUserId(
            @AuthenticationPrincipal FlutterUser currentUser
    ) {
        var response = service.getByUserId(currentUser.getId());
        return ContentModel.okContentModel(response);
    }

    @PutMapping("/products")
    public ContentModel<List<OrderProductModel>> addToBucket(
            @AuthenticationPrincipal FlutterUser currentUser,
            @RequestBody List<BucketProductModel> request
    ) {
        var response = service.addToBucket(currentUser, request);
        return ContentModel.okContentModel(response);
    }

    @DeleteMapping("/products")
    public ContentModel<List<OrderProductModel>> removeFromBucket(
            @AuthenticationPrincipal FlutterUser currentUser,
            @RequestBody List<BucketProductModel>  request
    ) {
        var response = service.removeFromBucket(currentUser, request);
        return ContentModel.okContentModel(response);
    }

    @DeleteMapping("/clear")
    public ContentModel<String> clear(
            @AuthenticationPrincipal FlutterUser currentUser
    ) {
        service.clear(currentUser.getId());
        return ContentModel.deletedContentModel("The bucket is cleared");
    }
}

