package com.eteration.simplebanking.service.impl;

import com.eteration.simplebanking.dto.ApprovalDTO;
import com.eteration.simplebanking.dto.BankAccountDTO;
import com.eteration.simplebanking.dto.BankAccountDTO;
import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.entity.BankAccountEntity;
import com.eteration.simplebanking.repository.BankAccountRepository;
import com.eteration.simplebanking.service.IBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class BankAccountService implements IBankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionService transactionService;

    @Transactional
    public ApprovalDTO creditAccount(String accountNumber, double amount) {
        BankAccountEntity account = bankAccountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            ApprovalDTO approvalDTO = transactionService.creditAccount(accountNumber, amount);
            if (approvalDTO != null) {
                return approvalDTO;
            }
        }
        return null;
    }

    @Transactional
    public ApprovalDTO debitAccount(String accountNumber, double amount) {
        BankAccountEntity account = bankAccountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            ApprovalDTO approvalDTO = transactionService.debitAccount(accountNumber, amount);
            if (approvalDTO != null) {
                return approvalDTO;
            }
        }
        return null;
    }

    @Transactional(readOnly = true)
    public BankAccountDTO getAccountByAccountNumber(String accountNumber) {
        BankAccountEntity account = bankAccountRepository.findByAccountNumber(accountNumber);
        BankAccountDTO response = new BankAccountDTO();
        response.setAccountNumber(account.getAccountNumber());
        response.setOwner(account.getOwner());
        response.setBalance(account.getBalance());
        response.setCreateDate(account.getCreateDate());
        if (!CollectionUtils.isEmpty(account.getTransactions())) {
            List<TransactionDTO> transactionDtoList = account.getTransactions().stream()
                    .map(entity -> {
                        TransactionDTO dto = new TransactionDTO();
                        dto.setAmount(entity.getAmount());
                        dto.setApprovalCode(entity.getApprovalCode());
                        dto.setType(entity.getType());
                        dto.setDate(entity.getCreateDate());
                        return dto;
                    })
                    .toList();

            response.setTransactions(transactionDtoList);
        }
        return response;
    }
}


