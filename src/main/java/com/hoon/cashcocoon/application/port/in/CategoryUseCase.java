package com.hoon.cashcocoon.application.port.in;

import com.hoon.cashcocoon.adapter.in.category.request.CreateCategoryRequest;
import com.hoon.cashcocoon.adapter.in.category.request.UpdateCategoryRequest;
import com.hoon.cashcocoon.application.dto.CategoryDto;

import java.util.List;

public interface CategoryUseCase {
    CategoryDto createCategory(CreateCategoryRequest createCategoryRequest);

    List<CategoryDto> getCategories(long memberIdx);

    CategoryDto updateCategory(long idx, UpdateCategoryRequest updateCategoryRequest);
}
