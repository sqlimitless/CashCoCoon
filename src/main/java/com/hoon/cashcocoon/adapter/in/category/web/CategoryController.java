package com.hoon.cashcocoon.adapter.in.category.web;

import com.hoon.cashcocoon.adapter.in.category.request.CreateCategoryRequest;
import com.hoon.cashcocoon.adapter.in.category.request.UpdateCategoryRequest;
import com.hoon.cashcocoon.application.dto.CategoryDto;
import com.hoon.cashcocoon.application.dto.MemberDto;
import com.hoon.cashcocoon.application.port.in.CategoryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@Slf4j
public class CategoryController {

    private final CategoryUseCase categoryUseCase;

    @PostMapping("")
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        CategoryDto category = categoryUseCase.createCategory(createCategoryRequest);
        return ResponseEntity.ok(category);
    }

    @GetMapping("")
    public ResponseEntity<?> getCategories() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberDto memberDto = (MemberDto) authentication.getPrincipal();
        List<CategoryDto> categoryDtos = categoryUseCase.getCategories(memberDto.getIdx());
        return ResponseEntity.ok(categoryDtos);
    }

    @PatchMapping("/{idx}")
    public ResponseEntity<?> updateCategory(@PathVariable("idx") long idx, @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        CategoryDto updated = categoryUseCase.updateCategory(idx, updateCategoryRequest);
        return ResponseEntity.ok(updated);
    }
}
