package com.example.flutter.mapper;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.entity.Transaction;
import com.example.flutter.entity.enumeration.OperationType;
import com.example.flutter.model.create.TransactionCreateModel;
import com.example.flutter.model.get.TransactionModel;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static com.example.flutter.entity.enumeration.OperationType.PAYMENT;
import static com.example.flutter.util.Constants.PAYMENT_TARGET;
import static com.example.flutter.util.Constants.getRandomBank;

@Mapper
public interface TransactionMapper {

    TransactionModel toModel(Transaction transaction);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "amount", source = "request.amount")
    @Mapping(target = "type", expression = "java( type )")
    @Mapping(target = "target", source = "request", qualifiedByName = "targetToModel")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", source = "user")
    Transaction toEntity(TransactionCreateModel request, @Context OperationType type, FlutterUser user);

    @Named("targetToModel")
    default String generateTargetToModel(TransactionCreateModel request, @Context OperationType type) {
        if (type.equals(PAYMENT)) {
            return PAYMENT_TARGET;
        }

        if (request.target() == null) {
            return getRandomBank();
        }

        return request.target();
    }
}
