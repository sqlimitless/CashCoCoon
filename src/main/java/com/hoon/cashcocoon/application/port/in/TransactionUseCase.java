package com.hoon.cashcocoon.application.port.in;

import com.hoon.cashcocoon.adapter.in.transaction.request.CreateTransactionRequest;

public interface TransactionUseCase {
    void createTransaction(CreateTransactionRequest createTransactionRequest);
}
