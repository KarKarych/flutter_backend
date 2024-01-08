package com.example.flutter.service.impl;

import com.example.flutter.entity.Bucket;
import com.example.flutter.entity.FlutterUser;
import com.example.flutter.entity.Order;
import com.example.flutter.mapper.OrderMapper;
import com.example.flutter.model.filter.OrderFilter;
import com.example.flutter.model.get.OrderModel;
import com.example.flutter.repository.BucketRepository;
import com.example.flutter.repository.OrderRepository;
import com.example.flutter.service.BucketService;
import com.example.flutter.service.OrderService;
import com.example.flutter.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.flutter.entity.enumeration.OrderType.getRandom;
import static com.example.flutter.model.create.TransactionCreateModel.of;
import static com.example.flutter.util.exception.BadRequestException.Code.BUCKET_IS_EMPTY;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BucketRepository bucketRepository;
    private final OrderMapper orderMapper;
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
        var productsToBuy = bucketRepository.findByIdUserId(user.getId()).stream()
                .map(Bucket::getProduct)
                .collect(Collectors.toSet());

        if (productsToBuy.isEmpty()) {
            throw BUCKET_IS_EMPTY.get("login = " + user.getLogin());
        }

        var orderAmount = orderMapper.generateAmountToModel(productsToBuy);
        transactionService.pay(user, of(orderAmount));

        bucketService.clear(user.getId());

        var orderTransient = orderMapper.toEntity(getRandom(), productsToBuy, user);
        var orderPersisted = orderRepository.save(orderTransient);

        return orderMapper.toModel(orderPersisted);
    }
}
