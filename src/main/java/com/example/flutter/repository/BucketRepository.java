package com.example.flutter.repository;

import com.example.flutter.entity.Bucket;
import com.example.flutter.entity.FlutterUser;
import com.example.flutter.entity.Product;
import com.example.flutter.entity.composite.BucketId;
import com.example.flutter.repository.base.ExtendedRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface BucketRepository extends ExtendedRepository<Bucket, BucketId> {

    @Query(value = """
            SELECT  p
             FROM   Bucket b
              JOIN  b.product p
             WHERE  b.user.id = :userId
             ORDER BY p.name
            """)
    List<Product> findByIdUserId(UUID userId);

    void deleteByUserAndProductIdIn(FlutterUser user, Collection<UUID> ids);

    void deleteByIdUserId(UUID userId);
}