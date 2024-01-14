package com.example.flutter.model.update;

import com.example.flutter.entity.enumeration.SizeType;
import com.example.flutter.util.validation.group.CreateGroup;
import com.example.flutter.util.validation.group.DeleteGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record BucketProductModel(
        @NotNull(groups = {CreateGroup.class, DeleteGroup.class}) UUID productId,
        @NotNull(groups = {CreateGroup.class, DeleteGroup.class}) SizeType size,
        @NotNull(groups = {CreateGroup.class, DeleteGroup.class})
        @Positive(groups = CreateGroup.class)
        @PositiveOrZero(groups = DeleteGroup.class) Integer amount
) {
}
