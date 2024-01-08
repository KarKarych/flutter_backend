package com.example.flutter.service.impl;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.mapper.BucketMapper;
import com.example.flutter.mapper.ProductMapper;
import com.example.flutter.model.get.ProductModel;
import com.example.flutter.model.update.BucketProductModel;
import com.example.flutter.repository.BucketRepository;
import com.example.flutter.repository.ProductRepository;
import com.example.flutter.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BucketServiceImpl implements BucketService {

    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final BucketMapper bucketMapper;

    @Override
    public List<ProductModel> getByUserId(UUID userId) {
        return bucketRepository.findByIdUserId(userId).stream()
                .map(productMapper::toModel)
                .toList();
    }

    @Override
    @Transactional
    public List<ProductModel> addToBucket(FlutterUser user, BucketProductModel request) {
        productRepository.findAllById(request.productIds()).stream()
                .map(product -> bucketMapper.toEntity(product, user))
                .forEach(bucketRepository::save);
        return getByUserId(user.getId());
    }

    @Override
    @Transactional
    public List<ProductModel> removeFromBucket(FlutterUser user, BucketProductModel request) {
        bucketRepository.deleteByUserAndProductIdIn(user, request.productIds());
        return getByUserId(user.getId());
    }

    @Override
    @Transactional
    public void clear(UUID userId) {
        bucketRepository.deleteByIdUserId(userId);
    }
}
