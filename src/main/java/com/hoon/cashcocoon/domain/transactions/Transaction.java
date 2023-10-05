package com.hoon.cashcocoon.domain.transactions;

import com.hoon.cashcocoon.domain.BaseEntity;
import jakarta.persistence.*;
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

    private LocalDateTime date;

    @Embedded
    private Money amount;

    @Enumerated(EnumType.STRING)
    private EntryType entryType;

    private String memo;
}
