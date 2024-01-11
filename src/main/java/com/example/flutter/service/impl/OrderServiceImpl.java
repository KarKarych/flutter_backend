package com.example.flutter.service.impl;

import com.example.flutter.entity.Bucket;
import com.example.flutter.entity.FlutterUser;
import com.example.flutter.entity.Order;
import com.example.flutter.mapper.OrderMapper;
import com.example.flutter.mapper.OrderProductMapper;
import com.example.flutter.model.filter.OrderFilter;
import com.example.flutter.model.get.OrderModel;
import com.example.flutter.repository.BucketRepository;
import com.example.flutter.repository.OrderProductRepository;
import com.example.flutter.repository.OrderRepository;
import com.example.flutter.service.BucketService;
import com.example.flutter.service.OrderService;
import com.example.flutter.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.example.flutter.entity.enumeration.OrderType.getRandom;
import static com.example.flutter.model.create.TransactionCreateModel.of;
import static com.example.flutter.util.exception.BadRequestException.Code.BUCKET_IS_EMPTY;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final BucketRepository bucketRepository;
    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;
    private final BucketService bucketService;
    private final TransactionService transactionService;

    @Override
    public List<OrderModel> getByUserId(UUID userId, OrderFilter orderFilter) {
        List<Order> orders;
        if (orderFilter.status() == null) {
            orders = orderRepository.findByUserIdOrderByCreatedAtDesc(userId);
        } else {
            orders = orderRepository.findByUserIdAndStatusOrderByCreatedAtDesc(userId, orderFilter.status());
        }

        return orders.stream()
                .map(orderMapper::toModel)
                .toList();
    }

    @Override
    @Transactional
    public OrderModel createFromBucket(FlutterUser user) {
        var productsToBuy = bucketRepository.findByUserId(user.getId());

        if (productsToBuy.isEmpty()) {
            throw BUCKET_IS_EMPTY.get(user.getLogin());
        }

        var orderAmount = calculateAmount(productsToBuy);
        transactionService.pay(user, of(orderAmount));

        var orderTransient = orderMapper.toEntity(getRandom(), user);
        var orderPersisted = saveOrder(orderTransient, productsToBuy);

        bucketService.clear(user.getId());

        return orderMapper.toModel(orderPersisted);
    }

    private Order saveOrder(Order orderTransient, List<Bucket> productsToBuy) {
        var orderPersisted = orderRepository.save(orderTransient);
        var orderProductsTransient = orderProductMapper.toEntities(orderPersisted, productsToBuy);
        orderProductRepository.saveAllAndFlush(orderProductsTransient);
        orderRepository.refresh(orderPersisted);
        return orderPersisted;
    }

    private Integer calculateAmount(List<Bucket> buckets) {
        return buckets.stream()
                .map(bucket -> bucket.getAmount() * bucket.getProduct().getPrice())
                .reduce(0, Integer::sum);
    }
}
