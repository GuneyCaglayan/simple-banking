package com.eteration.simplebanking.service.impl;

import com.eteration.simplebanking.dto.ApprovalDTO;
import com.eteration.simplebanking.entity.BankAccountEntity;
import com.eteration.simplebanking.entity.TransactionEntity;
import com.eteration.simplebanking.enums.TransactionTypeEnums;
import com.eteration.simplebanking.repository.BankAccountRepository;
import com.eteration.simplebanking.repository.TransactionRepository;
import com.eteration.simplebanking.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public ApprovalDTO creditAccount(String accountNumber, double amount) {
        BankAccountEntity account = bankAccountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            TransactionEntity transaction = new TransactionEntity();
            transaction.setBankAccount(account);
            transaction.setCreateDate(LocalDateTime.now());
            transaction.setAmount(amount);
            transaction.setType(TransactionTypeEnums.DEPOSIT);
            transaction.setApprovalCode(generateApprovalCode());
            transactionRepository.save(transaction);
            return new ApprovalDTO("OK", transaction.getApprovalCode());
        }
        return null;
    }

    @Transactional
    public ApprovalDTO debitAccount(String accountNumber, double amount) {
        BankAccountEntity account = bankAccountRepository.findByAccountNumber(accountNumber);
        if (account != null && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            TransactionEntity transaction = new TransactionEntity();
            transaction.setBankAccount(account);
            transaction.setCreateDate(LocalDateTime.now());
            transaction.setAmount(amount);
            transaction.setType(TransactionTypeEnums.WITHDRAWAL);
            transaction.setApprovalCode(generateApprovalCode());
            transactionRepository.save(transaction);
            return new ApprovalDTO("OK", transaction.getApprovalCode());
        }
        return null;
    }

    private String generateApprovalCode() {
        return java.util.UUID.randomUUID().toString();
    }
}

