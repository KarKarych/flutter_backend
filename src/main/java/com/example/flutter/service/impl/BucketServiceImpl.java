package com.example.flutter.service.impl;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.mapper.BucketMapper;
import com.example.flutter.model.get.OrderProductModel;
import com.example.flutter.model.update.BucketProductModel;
import com.example.flutter.repository.BucketRepository;
import com.example.flutter.repository.ProductRepository;
import com.example.flutter.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.example.flutter.util.exception.NotFoundException.Code.PRODUCT_IN_BUCKET_NOT_FOUND;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BucketServiceImpl implements BucketService {

    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final BucketMapper bucketMapper;

    @Override
    public List<OrderProductModel> getByUserId(UUID userId) {
        return bucketRepository.findByUserId(userId).stream()
                .map(bucketMapper::toModel)
                .toList();
    }

    @Override
    @Transactional
    public List<OrderProductModel> addToBucket(FlutterUser user, List<BucketProductModel> request) {
        request.stream()
                .map(model -> bucketMapper.toEntity(model, productRepository.findById(model.productId()), user))
                .forEach(bucketRepository::save);
        return getByUserId(user.getId());
    }

    @Override
    @Transactional
    public List<OrderProductModel> removeFromBucket(FlutterUser user, List<BucketProductModel> request) {
        request.forEach(model -> changeBucketProductAmount(user, model));
        return getByUserId(user.getId());
    }

    @Override
    @Transactional
    public List<OrderProductModel> changeProductAmount(FlutterUser user, BucketProductModel request) {
        changeBucketProductAmount(user, request);
        return getByUserId(user.getId());
    }

    @Override
    @Transactional
    public void clear(UUID userId) {
        bucketRepository.deleteByIdUserId(userId);
    }

    private void changeBucketProductAmount(FlutterUser user, BucketProductModel model) {
        var bucketPersisted = bucketRepository.findByUserAndProductIdAndSize(user, model.productId(), model.size())
                .orElseThrow(() -> PRODUCT_IN_BUCKET_NOT_FOUND.get(user.getLogin(), "productId = %s".formatted(model.productId())));

        var amountRequest = model.amount();

        if (amountRequest == 0) {
            bucketRepository.delete(bucketPersisted);
            return;
        }

        bucketPersisted.setAmount(amountRequest);
    }
}
