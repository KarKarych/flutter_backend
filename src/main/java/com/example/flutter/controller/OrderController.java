package com.example.flutter.controller;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.entity.enumeration.OrderStatus;
import com.example.flutter.model.basic.ContentModel;
import com.example.flutter.model.filter.OrderFilter;
import com.example.flutter.model.get.OrderModel;
import com.example.flutter.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@SecurityRequirement(name = "bearer")
@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderController {

    private final OrderService service;

    @GetMapping
    public ContentModel<List<OrderModel>> getByUserId(
            @AuthenticationPrincipal FlutterUser currentUser,
            @RequestParam(required = false) OrderStatus status
    ) {
        var response = service.getByUserId(currentUser.getId(), new OrderFilter(status));
        return ContentModel.okContentModel(response);
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public ContentModel<OrderModel> createFromBucket(
            @AuthenticationPrincipal FlutterUser currentUser
    ) {
        var response = service.createFromBucket(currentUser);
        return ContentModel.okContentModel(response);
    }
}

