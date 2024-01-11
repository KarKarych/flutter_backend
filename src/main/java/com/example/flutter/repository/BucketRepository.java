package com.example.flutter.repository;

import com.example.flutter.entity.Bucket;
import com.example.flutter.entity.FlutterUser;
import com.example.flutter.entity.composite.BucketId;
import com.example.flutter.repository.base.ExtendedRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BucketRepository extends ExtendedRepository<Bucket, BucketId> {

    @Query(value = """
            SELECT  b
             FROM   Bucket b
              JOIN FETCH b.product p
             WHERE  b.user.id = :userId
             ORDER BY p.name
            """)
    List<Bucket> findByUserId(UUID userId);

    @Query(value = """
            SELECT  b
             FROM   Bucket b
             WHERE  b.user = :user AND
                    b.product.id = :productId
             
            """)
    Optional<Bucket> findByUserAndProductId(FlutterUser user, UUID productId);

    void deleteByIdUserId(UUID userId);
}