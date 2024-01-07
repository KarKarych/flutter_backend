package com.example.flutter.repository;

import com.example.flutter.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("""
            SELECT  p
             FROM   Product p
             WHERE  p.name ILIKE :searchQuery OR
                    p.description ILIKE :searchQuery
             ORDER BY p.name
            """)
    List<Product> findBySearchQuery(String searchQuery);
}