package com.hoon.cashcocoon.adapter.in.category.request;

import com.hoon.cashcocoon.domain.category.EntryType;
import lombok.Data;

@Data
public class UpdateCategoryRequest {

    private EntryType entryType;
    private String name;
}
