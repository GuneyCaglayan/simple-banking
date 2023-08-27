package com.eteration.simplebanking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankAccountDTO {
    private String accountNumber;
    private String owner;
    private double balance;
    private LocalDateTime createDate;
    private List<TransactionDTO> transactions;
}

