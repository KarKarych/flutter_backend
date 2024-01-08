package com.example.flutter.controller;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.model.basic.ContentModel;
import com.example.flutter.model.create.TransactionCreateModel;
import com.example.flutter.model.get.TransactionModel;
import com.example.flutter.service.TransactionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@SecurityRequirement(name = "bearer")
@RequiredArgsConstructor
@RequestMapping("/transactions")
@RestController
public class TransactionController {

    private final TransactionService service;

    @GetMapping
    public ContentModel<List<TransactionModel>> getByUserId(
            @AuthenticationPrincipal FlutterUser currentUser
    ) {
        var response = service.getByUserId(currentUser.getId());
        return ContentModel.okContentModel(response);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/top-up")
    public ContentModel<TransactionModel> topUp(
            @AuthenticationPrincipal FlutterUser currentUser,
            @RequestBody TransactionCreateModel request
    ) {
        var response = service.topUp(currentUser, request);
        return ContentModel.okContentModel(response);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/pay")
    public ContentModel<TransactionModel> pay(
            @AuthenticationPrincipal FlutterUser currentUser,
            @RequestBody TransactionCreateModel request
    ) {
        var response = service.pay(currentUser, request);
        return ContentModel.okContentModel(response);
    }
}
