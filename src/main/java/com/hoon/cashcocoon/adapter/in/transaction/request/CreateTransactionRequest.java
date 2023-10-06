package com.hoon.cashcocoon.adapter.in.transaction.request;

import com.hoon.cashcocoon.domain.transactions.EntryType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateTransactionRequest {

    private LocalDateTime date;

    private long amount;

    private EntryType entryType;

    private String memo;
}
