package com.example.flutter.service;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.model.create.TransactionCreateModel;
import com.example.flutter.model.get.TransactionModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface TransactionService {

    List<TransactionModel> getByUserId(@NotNull UUID userId);

    TransactionModel topUp(
            @NotNull FlutterUser user,
            @Valid @NotNull TransactionCreateModel request
    );

    TransactionModel pay(
            @NotNull FlutterUser user,
            @Valid @NotNull TransactionCreateModel request
    );
}
