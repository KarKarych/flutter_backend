package com.example.flutter.service.impl;

import com.example.flutter.entity.Product;
import com.example.flutter.mapper.ProductMapper;
import com.example.flutter.model.filter.ProductFilter;
import com.example.flutter.model.get.ProductModel;
import com.example.flutter.repository.ProductRepository;
import com.example.flutter.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.flutter.util.Constants.SEARCH_PATTERN;
import static com.example.flutter.util.exception.NotFoundException.Code.PRODUCT_NOT_FOUND;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductModel> getAll(ProductFilter filterRequest) {
        var query = filterRequest.searchQuery();

        List<Product> products;
        if (query == null) {
            products = productRepository.findAll();
        } else {
            products = productRepository.findBySearchQuery(SEARCH_PATTERN.formatted(query.strip()));
        }

        return products.stream()
                .map(productMapper::toModel)
                .toList();
    }

    @Override
    public ProductModel getById(UUID id) {
        return Optional.of(id)
                .flatMap(productRepository::findById)
                .map(productMapper::toModel)
                .orElseThrow(PRODUCT_NOT_FOUND::get);
    }
}
