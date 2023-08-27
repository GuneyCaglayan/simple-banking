package com.eteration.simplebanking.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bank_account")
public class BankAccountEntity extends BaseEntity implements Serializable {

    @Column(name = "owner")
    private String owner;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private double balance;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<TransactionEntity> transactions;
}

