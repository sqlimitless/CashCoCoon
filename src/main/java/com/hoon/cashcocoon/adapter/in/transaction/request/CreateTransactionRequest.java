package com.hoon.cashcocoon.adapter.in.transaction.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateTransactionRequest {

    private long categoryIdx;

    private LocalDateTime date;

    private long amount;

    private String memo;
}
