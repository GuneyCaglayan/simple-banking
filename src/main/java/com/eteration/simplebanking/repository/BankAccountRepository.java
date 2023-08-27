package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
    BankAccountEntity findByAccountNumber(String accountNumber);
}

