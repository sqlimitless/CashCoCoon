package com.hoon.cashcocoon.application.service.transaction;

import com.hoon.cashcocoon.adapter.in.transaction.request.CreateTransactionRequest;
import com.hoon.cashcocoon.adapter.in.transaction.request.UpdateTransactionRequest;
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
    @Transactional(readOnly = true)
    public List<TransactionDto> getTransactions(long idx) {
        List<Transaction> transactions = jpaTransactionRepository.findByMemberIdx(idx);
        return transactions.stream().map(TransactionDto::of).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionDto getTransactionDetail(long idx) {
        Transaction transaction = jpaTransactionRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("없는 idx"));
        return TransactionDto.of(transaction);
    }

    @Override
    @Transactional
    public TransactionDto updateTransaction(long idx, UpdateTransactionRequest updateTransactionRequest) {
        Transaction transaction = jpaTransactionRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("없는 idx"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberDto memberDto = (MemberDto) authentication.getPrincipal();
        if (transaction.getMemberIdx() != memberDto.getIdx()) {
            throw new IllegalArgumentException("수정 권한이 없음");
        }
        Transaction updated = transaction.updateTransaction(updateTransactionRequest.getCategoryIdx(), updateTransactionRequest.getDate(), updateTransactionRequest.getAmount(), updateTransactionRequest.getMemo());
        return TransactionDto.of(updated);
    }

    @Override
    @Transactional
    public void deleteTransaction(long idx) {
        Transaction transaction = jpaTransactionRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("없는 idx"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberDto memberDto = (MemberDto) authentication.getPrincipal();
        if (transaction.getMemberIdx() != memberDto.getIdx()) {
            throw new IllegalArgumentException("수정 권한이 없음");
        }
        jpaTransactionRepository.delete(transaction);
    }
}
