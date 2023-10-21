package com.hoon.cashcocoon.application.service.category;

import com.hoon.cashcocoon.adapter.in.category.request.CreateCategoryRequest;
import com.hoon.cashcocoon.adapter.in.category.request.UpdateCategoryRequest;
import com.hoon.cashcocoon.adapter.out.persistance.JpaCategoryRepository;
import com.hoon.cashcocoon.application.dto.CategoryDto;
import com.hoon.cashcocoon.application.dto.MemberDto;
import com.hoon.cashcocoon.application.port.in.CategoryUseCase;
import com.hoon.cashcocoon.domain.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryUseCase {

    private final JpaCategoryRepository jpaCategoryRepository;
    @Override
    @Transactional
    public CategoryDto createCategory(CreateCategoryRequest createCategoryRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberDto memberDto = (MemberDto) authentication.getPrincipal();
        CategoryDto categoryDto = CategoryDto.builder()
                .name(createCategoryRequest.getName())
                .entryType(createCategoryRequest.getEntryType())
                .memberIdx(memberDto.getIdx())
                .build();
        Category save = jpaCategoryRepository.save(categoryDto.toEntity());
        return CategoryDto.of(save);
    }

    @Override
    @Transactional
    public List<CategoryDto> getCategories(long memberIdx) {
        List<Category> categories = jpaCategoryRepository.findByMemberIdx(memberIdx);
        return categories.stream().map(CategoryDto::of).toList();
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(long idx, UpdateCategoryRequest updateCategoryRequest) {
        Category category = jpaCategoryRepository.findById(idx).orElse(null);

        return null;
    }
}
