package com.eteration.simplebanking.dto;

import com.eteration.simplebanking.enums.TransactionTypeEnums;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class TransactionDTO {

    private LocalDateTime date;

    private double amount;

    private TransactionTypeEnums type;

    private String approvalCode;
}
