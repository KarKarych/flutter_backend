package com.example.flutter.repository;

import com.example.flutter.entity.Order;
import com.example.flutter.entity.enumeration.OrderType;
import com.example.flutter.repository.base.ExtendedRepository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends ExtendedRepository<Order, UUID> {

    @EntityGraph(attributePaths = {"products"})
    List<Order> findByUserIdOrderByCreatedAtDesc(UUID userId);

    @EntityGraph(attributePaths = {"products"})
    List<Order> findByUserIdAndStatusOrderByCreatedAtDesc(UUID userId, OrderType orderType);
}