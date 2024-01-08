package com.example.flutter.repository;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.entity.Product;
import com.example.flutter.entity.Wishlist;
import com.example.flutter.entity.composite.WishlistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface WishlistRepository extends JpaRepository<Wishlist, WishlistId> {

    @Query(value = """
            SELECT  p
             FROM   Wishlist w
              JOIN  w.product p
             WHERE  w.user.id = :userId
             ORDER BY p.name
            """)
    List<Product> findByIdUserId(UUID userId);

    void deleteByUserAndProductIdIn(FlutterUser user, Collection<UUID> ids);

    void deleteByIdUserId(UUID userId);
}