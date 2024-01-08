package com.example.flutter.repository;

import com.example.flutter.entity.Order;
import com.example.flutter.entity.enumeration.OrderType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    @EntityGraph(attributePaths = {"products"})
    List<Order> findByUserIdOrderByCreatedAtDesc(UUID userId);

    @EntityGraph(attributePaths = {"products"})
    List<Order> findByUserIdAndStatusOrderByCreatedAtDesc(UUID userId, OrderType orderType);
}