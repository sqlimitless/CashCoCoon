package com.hoon.cashcocoon.domain.category;

import com.hoon.cashcocoon.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table(name = "category")
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    private long memberIdx;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EntryType entryType;

    private String name;

}
