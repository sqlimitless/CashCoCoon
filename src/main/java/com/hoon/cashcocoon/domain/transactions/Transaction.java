package com.hoon.cashcocoon.domain.transactions;

import com.hoon.cashcocoon.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

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
    private LocalDateTime date;

    @Embedded
    @NotNull
    private Money amount;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EntryType entryType;

    private String memo;
}
