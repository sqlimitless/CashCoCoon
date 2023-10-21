package com.hoon.cashcocoon.application.port.in;

import com.hoon.cashcocoon.adapter.in.transaction.request.CreateTransactionRequest;
import com.hoon.cashcocoon.application.dto.TransactionDto;

import java.util.List;

public interface TransactionUseCase {
    TransactionDto createTransaction(CreateTransactionRequest createTransactionRequest);

    List<TransactionDto> getTransactions(long idx);

    TransactionDto getTransactionDetail(long idx);
}
