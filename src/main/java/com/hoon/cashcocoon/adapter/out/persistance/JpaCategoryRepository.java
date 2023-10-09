package com.hoon.cashcocoon.adapter.out.persistance;

import com.hoon.cashcocoon.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, Long> {
}
