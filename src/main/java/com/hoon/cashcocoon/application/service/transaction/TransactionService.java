package com.hoon.cashcocoon.application.service.transaction;

import com.hoon.cashcocoon.adapter.in.transaction.request.CreateTransactionRequest;
import com.hoon.cashcocoon.adapter.out.persistance.JpaTransactionRepository;
import com.hoon.cashcocoon.application.dto.MemberDto;
import com.hoon.cashcocoon.application.dto.TransactionDto;
import com.hoon.cashcocoon.application.port.in.TransactionUseCase;
import com.hoon.cashcocoon.domain.transactions.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService implements TransactionUseCase {

    private final JpaTransactionRepository jpaTransactionRepository;
    @Override
    @Transactional
    public void createTransaction(CreateTransactionRequest createTransactionRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberDto memberDto = (MemberDto) authentication.getPrincipal();
        TransactionDto transactionDto = TransactionDto.builder()
                .amount(new Money(createTransactionRequest.getAmount()))
                .date(createTransactionRequest.getDate())
                .memo(createTransactionRequest.getMemo())
                .categoryIdx(createTransactionRequest.getCategoryIdx())
                .memberIdx(memberDto.getIdx())
                .build();
        jpaTransactionRepository.save(transactionDto.toEntity());
    }

    @Override
    @Transactional
    public TransactionDto getTransactions(long idx) {
        System.out.println("idx = " + idx);
        return null;
    }
}
