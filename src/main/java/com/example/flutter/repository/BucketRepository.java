package com.example.flutter.repository;

import com.example.flutter.entity.Bucket;
import com.example.flutter.entity.FlutterUser;
import com.example.flutter.entity.composite.BucketId;
import com.example.flutter.entity.enumeration.SizeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BucketRepository extends JpaRepository<Bucket, BucketId> {

    @Query(value = """
            SELECT  b
             FROM   Bucket b
              JOIN FETCH b.product
             WHERE  b.user.id = :userId
             ORDER BY b.createdAt
            """)
    List<Bucket> findByUserId(UUID userId);

    @Query(value = """
            SELECT  b
             FROM   Bucket b
             WHERE  b.user = :user AND
                    b.product.id = :productId AND
                    b.id.size = :size
            """)
    Optional<Bucket> findByUserAndProductIdAndSize(FlutterUser user, UUID productId, SizeType size);

    void deleteByIdUserId(UUID userId);
}