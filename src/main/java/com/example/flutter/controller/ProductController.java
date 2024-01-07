package com.example.flutter.controller;

import com.example.flutter.model.basic.ContentModel;
import com.example.flutter.model.filter.ProductFilter;
import com.example.flutter.model.get.ProductModel;
import com.example.flutter.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@SecurityRequirement(name = "bearer")
@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ContentModel<List<ProductModel>> getAll(@RequestParam(required = false) String searchQuery) {
        var response = service.getAll(new ProductFilter(searchQuery));
        return ContentModel.okContentModel(response);
    }

    @GetMapping("/{id}")
    public ContentModel<ProductModel> getById(@PathVariable UUID id) {
        var response = service.getById(id);
        return ContentModel.okContentModel(response);
    }
}

