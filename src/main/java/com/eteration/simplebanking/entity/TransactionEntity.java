package com.eteration.simplebanking.entity;

import com.eteration.simplebanking.enums.TransactionTypeEnums;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class TransactionEntity extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    @EqualsAndHashCode.Exclude
    private BankAccountEntity bankAccount;

    @Column(name = "amount")
    private double amount;

    @Column(name = "type")
    private TransactionTypeEnums type;

    @Column(name = "approval_code")
    private String approvalCode;
}
