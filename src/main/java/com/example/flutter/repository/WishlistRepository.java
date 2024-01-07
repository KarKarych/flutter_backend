package com.example.flutter.repository;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.entity.Wishlist;
import com.example.flutter.entity.composite.WishlistId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface WishlistRepository extends JpaRepository<Wishlist, WishlistId> {

    @EntityGraph(attributePaths = {"product"})
    List<Wishlist> findByIdUserId(UUID userId);

    void deleteByUserAndProductIdIn(FlutterUser user, Collection<UUID> ids);

    void deleteByIdUserId(UUID userId);
}