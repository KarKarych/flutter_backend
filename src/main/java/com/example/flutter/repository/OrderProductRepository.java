package com.example.flutter.repository;

import com.example.flutter.entity.OrderProduct;
import com.example.flutter.entity.composite.OrderProductId;
import com.example.flutter.repository.base.ExtendedRepository;

public interface OrderProductRepository extends ExtendedRepository<OrderProduct, OrderProductId> {
}