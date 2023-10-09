package com.hoon.cashcocoon.application.dto;

import com.hoon.cashcocoon.domain.category.Category;
import com.hoon.cashcocoon.domain.category.EntryType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {

    private long idx;
    private long memberIdx;
    private EntryType entryType;
    private String name;

    public Category toEntity(){
        return Category.builder()
                .idx(this.idx)
                .memberIdx(this.memberIdx)
                .entryType(this.entryType)
                .name(this.name)
                .build();
    }

    public static CategoryDto of (Category category) {
        return CategoryDto.builder()
                .idx(category.getIdx())
                .memberIdx(category.getMemberIdx())
                .entryType(category.getEntryType())
                .name(category.getName())
                .build();
    }
}
