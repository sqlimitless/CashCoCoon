package com.hoon.cashcocoon.adapter.in.transaction.web;

import com.hoon.cashcocoon.adapter.in.transaction.request.CreateTransactionRequest;
import com.hoon.cashcocoon.application.dto.MemberDto;
import com.hoon.cashcocoon.application.dto.TransactionDto;
import com.hoon.cashcocoon.application.port.in.TransactionUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
@Slf4j
public class TransactionController {

    private final TransactionUseCase transactionUseCase;

    @PostMapping("")
    public ResponseEntity<?> createTransaction(@RequestBody CreateTransactionRequest createTransactionRequest) {
        TransactionDto transaction = transactionUseCase.createTransaction(createTransactionRequest);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("")
    public ResponseEntity<?> getTransactions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberDto memberDto = (MemberDto) authentication.getPrincipal();
        List<TransactionDto> transactions = transactionUseCase.getTransactions(memberDto.getIdx());
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> getTransactionDetail(@PathVariable("idx") long idx) {
        TransactionDto transactionDetail = transactionUseCase.getTransactionDetail(idx);
        return ResponseEntity.ok(transactionDetail);
    }

    @PatchMapping("/{idx}")
    public ResponseEntity<?> updateTransactionDetail(@PathVariable long idx) {
        return null;
    }

    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deleteTransactionDetail(@PathVariable long idx) {
        return null;
    }
}
