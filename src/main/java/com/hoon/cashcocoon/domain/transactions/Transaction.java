package com.hoon.cashcocoon.domain.transactions;

import com.hoon.cashcocoon.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Table(name = "transaction")
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Transaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @NotNull
    private long memberIdx;

    private long categoryIdx;

    @NotNull
    private LocalDate date;

    @Embedded
    @NotNull
    private Money amount;

    private String memo;

    public Transaction updateTransaction(long categoryIdx, LocalDate date, Money amount, String memo) {
        this.categoryIdx = categoryIdx;
        this.date = date;
        this.amount = amount;
        this.memo = memo;
        return this;
    }
}
