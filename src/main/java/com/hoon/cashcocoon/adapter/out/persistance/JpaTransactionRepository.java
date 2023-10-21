package com.hoon.cashcocoon.adapter.out.persistance;

import com.hoon.cashcocoon.domain.transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaTransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByMemberIdx(long idx);
}
