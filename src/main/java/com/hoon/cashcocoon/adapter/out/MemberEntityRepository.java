package com.hoon.cashcocoon.adapter.out;

import com.hoon.cashcocoon.adapter.out.persistance.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long> {
    Optional<UserDetails> findByEmail(String userId);
}
