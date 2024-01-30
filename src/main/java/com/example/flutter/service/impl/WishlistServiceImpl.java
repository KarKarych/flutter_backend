package com.example.flutter.service.impl;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.mapper.ProductMapper;
import com.example.flutter.mapper.WishlistMapper;
import com.example.flutter.model.get.ProductModel;
import com.example.flutter.model.update.WishlistProductModel;
import com.example.flutter.repository.ProductRepository;
import com.example.flutter.repository.WishlistRepository;
import com.example.flutter.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final WishlistMapper wishlistMapper;

    @Override
    public List<ProductModel> getByUserId(UUID userId) {
        return wishlistRepository.findByIdUserId(userId).stream()
                .map(productMapper::toModel)
                .toList();
    }

    @Override
    @Transactional
    public List<ProductModel> addToWishlist(FlutterUser user, WishlistProductModel request) {
        productRepository.findAllById(request.productIds()).stream()
                .map(product -> wishlistMapper.toEntity(product, user))
                .forEach(wishlistRepository::save);
        return getByUserId(user.getId());
    }

    @Override
    @Transactional
    public List<ProductModel> removeFromWishlist(FlutterUser user, WishlistProductModel request) {
        wishlistRepository.deleteByUserAndProductIdIn(user, request.productIds());
        return getByUserId(user.getId());
    }

    @Override
    @Transactional
    public void clear(UUID userId) {
        wishlistRepository.deleteByIdUserId(userId);
    }
}
