package com.eteration.simplebanking.service;

import com.eteration.simplebanking.dto.ApprovalDTO;
import com.eteration.simplebanking.dto.BankAccountDTO;

public interface IBankAccountService {
    ApprovalDTO creditAccount(String accountNumber, double amount);
    ApprovalDTO debitAccount(String accountNumber, double amount);
    BankAccountDTO getAccountByAccountNumber(String accountNumber);
}

