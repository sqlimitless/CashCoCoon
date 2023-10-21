package com.hoon.cashcocoon.application.service.transaction;

import com.hoon.cashcocoon.adapter.in.transaction.request.CreateTransactionRequest;
import com.hoon.cashcocoon.adapter.out.persistance.JpaTransactionRepository;
import com.hoon.cashcocoon.application.dto.MemberDto;
import com.hoon.cashcocoon.application.dto.TransactionDto;
import com.hoon.cashcocoon.application.port.in.TransactionUseCase;
import com.hoon.cashcocoon.domain.transactions.Money;
import com.hoon.cashcocoon.domain.transactions.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService implements TransactionUseCase {

    private final JpaTransactionRepository jpaTransactionRepository;
    @Override
    @Transactional
    public TransactionDto createTransaction(CreateTransactionRequest createTransactionRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberDto memberDto = (MemberDto) authentication.getPrincipal();
        TransactionDto transactionDto = TransactionDto.builder()
                .amount(new Money(createTransactionRequest.getAmount()))
                .date(createTransactionRequest.getDate())
                .memo(createTransactionRequest.getMemo())
                .categoryIdx(createTransactionRequest.getCategoryIdx())
                .memberIdx(memberDto.getIdx())
                .build();
        Transaction saved = jpaTransactionRepository.save(transactionDto.toEntity());
        return TransactionDto.of(saved);
    }

    @Override
    @Transactional
    public List<TransactionDto> getTransactions(long idx) {
        List<Transaction> transactions = jpaTransactionRepository.findByMemberId(idx);
        return transactions.stream().map(TransactionDto::of).toList();
    }

    @Override
    public TransactionDto getTransactionDetail(long idx) {
        Transaction transaction = jpaTransactionRepository.findById(idx).orElse(null);
        return TransactionDto.of(transaction);
    }
}
