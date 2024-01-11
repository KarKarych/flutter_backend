package com.example.flutter.service;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.model.get.OrderProductModel;
import com.example.flutter.model.update.BucketProductModel;
import com.example.flutter.util.validation.group.CreateGroup;
import com.example.flutter.util.validation.group.DeleteGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface BucketService {

    List<OrderProductModel> getByUserId(@NotNull UUID userId);

    @Validated(CreateGroup.class)
    List<OrderProductModel> addToBucket(
            @NotNull(groups = CreateGroup.class) FlutterUser flutterUser,
            @Valid @NotNull(groups = CreateGroup.class) List<BucketProductModel> request
    );

    @Validated(DeleteGroup.class)
    List<OrderProductModel> removeFromBucket(
            @NotNull(groups = DeleteGroup.class) FlutterUser flutterUser,
            @Valid @NotNull(groups = DeleteGroup.class) List<BucketProductModel> request
    );

    void clear(@NotNull UUID userId);
}
