package com.hoon.cashcocoon.adapter.out.persistance;

import com.hoon.cashcocoon.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaMemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String userId);

    boolean existsByEmail(String email);
}
