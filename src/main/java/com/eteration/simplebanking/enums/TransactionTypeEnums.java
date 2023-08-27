package com.eteration.simplebanking.enums;

public enum TransactionTypeEnums {
    DEPOSIT("DepositTransaction"),
    WITHDRAWAL("WithdrawalTransaction");

    private final String value;

    TransactionTypeEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

