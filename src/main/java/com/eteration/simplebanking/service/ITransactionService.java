package com.eteration.simplebanking.service;

import com.eteration.simplebanking.dto.ApprovalDTO;

public interface ITransactionService {
    ApprovalDTO creditAccount(String accountNumber, double amount);
    ApprovalDTO debitAccount(String accountNumber, double amount);
}

