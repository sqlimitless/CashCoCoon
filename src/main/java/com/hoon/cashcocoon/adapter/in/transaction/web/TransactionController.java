package com.hoon.cashcocoon.adapter.in.transaction.web;

import com.hoon.cashcocoon.adapter.in.transaction.request.CreateTransactionRequest;
import com.hoon.cashcocoon.application.port.in.TransactionUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
@Slf4j
public class TransactionController {

    private final TransactionUseCase transactionUseCase;

    @PostMapping("")
    public ResponseEntity<?> createTransaction(@RequestBody CreateTransactionRequest createTransactionRequest) {
        transactionUseCase.createTransaction(createTransactionRequest);
        return null;
    }
}
