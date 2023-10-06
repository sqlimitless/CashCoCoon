package com.hoon.cashcocoon.application.service.transaction;

import com.hoon.cashcocoon.adapter.in.transaction.request.CreateTransactionRequest;
import com.hoon.cashcocoon.application.port.in.TransactionUseCase;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements TransactionUseCase {

    @Override
    public void createTransaction(CreateTransactionRequest createTransactionRequest) {

    }
}
