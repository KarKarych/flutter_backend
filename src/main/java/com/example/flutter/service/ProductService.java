package com.example.flutter.service;

import com.example.flutter.model.filter.ProductFilter;
import com.example.flutter.model.get.ProductModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface ProductService {

    List<ProductModel> getAll(@NotNull ProductFilter filterRequest);

    ProductModel getById(@NotNull UUID id);
}
