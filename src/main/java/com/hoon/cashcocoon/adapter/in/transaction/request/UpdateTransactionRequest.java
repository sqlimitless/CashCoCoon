package com.hoon.cashcocoon.adapter.in.transaction.request;

import com.hoon.cashcocoon.domain.transactions.Money;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateTransactionRequest {
    private long idx;
    private long categoryIdx;
    private LocalDate date;
    private Money amount;
    private String memo;
}
