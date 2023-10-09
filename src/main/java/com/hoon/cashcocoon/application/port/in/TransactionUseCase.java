package com.hoon.cashcocoon.application.port.in;

import com.hoon.cashcocoon.adapter.in.transaction.request.CreateTransactionRequest;
import com.hoon.cashcocoon.application.dto.TransactionDto;

public interface TransactionUseCase {
    void createTransaction(CreateTransactionRequest createTransactionRequest);

    TransactionDto getTransactions(long idx);
}
