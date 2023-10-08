package com.hoon.cashcocoon.application.dto;

import com.hoon.cashcocoon.domain.transactions.EntryType;
import com.hoon.cashcocoon.domain.transactions.Money;
import com.hoon.cashcocoon.domain.transactions.Transaction;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDto {

    private long idx;
    private long memberIdx;
    private long categoryIdx;
    private LocalDateTime date;
    private Money amount;
    private EntryType entryType;
    private String memo;

    public Transaction toEntity() {
        return Transaction.builder()
                .idx(this.getIdx())
                .memberIdx(this.getMemberIdx())
                .categoryIdx(this.getCategoryIdx())
                .date(this.getDate())
                .entryType(this.getEntryType())
                .memo(this.getMemo())
                .build();
    }

    public static TransactionDto of(Transaction transaction) {
        return TransactionDto.builder()
                .idx(transaction.getIdx())
                .memberIdx(transaction.getMemberIdx())
                .categoryIdx(transaction.getCategoryIdx())
                .date(transaction.getDate())
                .entryType(transaction.getEntryType())
                .memo(transaction.getMemo())
                .build();
    }
}
