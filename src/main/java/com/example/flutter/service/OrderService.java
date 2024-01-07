package com.example.flutter.service;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.model.filter.OrderFilter;
import com.example.flutter.model.get.OrderModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface OrderService {

    List<OrderModel> getByUserId(@NotNull UUID userId, @NotNull OrderFilter orderFilter);

    OrderModel createFromBucket(@NotNull FlutterUser user);
}
