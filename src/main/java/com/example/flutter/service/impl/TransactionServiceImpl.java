package com.example.flutter.service.impl;

import com.example.flutter.entity.FlutterUser;
import com.example.flutter.mapper.TransactionMapper;
import com.example.flutter.model.create.TransactionCreateModel;
import com.example.flutter.model.get.TransactionModel;
import com.example.flutter.repository.TransactionRepository;
import com.example.flutter.repository.UserRepository;
import com.example.flutter.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.example.flutter.entity.enumeration.OperationType.PAYMENT;
import static com.example.flutter.entity.enumeration.OperationType.TOP_UP;
import static com.example.flutter.util.exception.BadRequestException.Code.INSUFFICIENT_BALANCE;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public List<TransactionModel> getByUserId(UUID userId) {
        return transactionRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(transactionMapper::toModel)
                .toList();
    }

    @Override
    @Transactional
    public TransactionModel topUp(FlutterUser user, TransactionCreateModel request) {
        var transactionTransient = transactionMapper.toEntity(request, TOP_UP, user);
        userRepository.updateBalanceById(user.getBalance() + request.amount(), user.getId());
        var transactionPersisted = transactionRepository.save(transactionTransient);
        return transactionMapper.toModel(transactionPersisted);
    }

    @Override
    @Transactional
    public TransactionModel pay(FlutterUser user, TransactionCreateModel request) {
        var amount = request.amount();
        var balance = user.getBalance();

        if (amount > balance) {
            throw INSUFFICIENT_BALANCE.get(user.getLogin());
        }
        userRepository.updateBalanceById(balance - amount, user.getId());

        var transactionTransient = transactionMapper.toEntity(request, PAYMENT, user);
        var transactionPersisted = transactionRepository.save(transactionTransient);
        return transactionMapper.toModel(transactionPersisted);
    }
}
