package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}

