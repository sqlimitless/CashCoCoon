package com.hoon.cashcocoon.application.port.in;

import com.hoon.cashcocoon.adapter.in.category.request.CreateCategoryRequest;
import com.hoon.cashcocoon.application.dto.CategoryDto;

public interface CategoryUseCase {
    CategoryDto createCategory(CreateCategoryRequest createCategoryRequest);
}
