package com.hoon.cashcocoon.adapter.out.persistance;

import com.hoon.cashcocoon.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByMemberIdx(long memberIdx);
}
