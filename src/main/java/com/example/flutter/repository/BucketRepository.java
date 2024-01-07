package com.example.flutter.repository;

import com.example.flutter.entity.Bucket;
import com.example.flutter.entity.FlutterUser;
import com.example.flutter.entity.composite.BucketId;
import com.example.flutter.repository.base.ExtendedRepository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface BucketRepository extends ExtendedRepository<Bucket, BucketId> {

    @EntityGraph(attributePaths = {"product"})
    List<Bucket> findByIdUserId(UUID userId);

    void deleteByUserAndProductIdIn(FlutterUser user, Collection<UUID> ids);

    void deleteByIdUserId(UUID userId);
}