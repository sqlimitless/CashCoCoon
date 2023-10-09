package com.hoon.cashcocoon.adapter.in.category.web;

import com.hoon.cashcocoon.adapter.in.category.request.CreateCategoryRequest;
import com.hoon.cashcocoon.application.dto.CategoryDto;
import com.hoon.cashcocoon.application.port.in.CategoryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categorys")
@Slf4j
public class CategoryController {

    private final CategoryUseCase categoryUseCase;

    @PostMapping("")
    public ResponseEntity<?> createCategory (@RequestBody CreateCategoryRequest createCategoryRequest) {
        CategoryDto category = categoryUseCase.createCategory(createCategoryRequest);
        return ResponseEntity.ok(category);
    }
}
