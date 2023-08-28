package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.BankAccountDTO;
import com.eteration.simplebanking.dto.ApprovalDTO;
import com.eteration.simplebanking.dto.CreditRequest;
import com.eteration.simplebanking.exception.NotFoundException;
import com.eteration.simplebanking.service.impl.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/v1")
public class BankAccountController {
    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<ApprovalDTO> creditAccount(@PathVariable String accountNumber,
                                                     @RequestBody CreditRequest creditRequest) {
        ApprovalDTO account = bankAccountService.creditAccount(accountNumber, creditRequest.getAmount());
        if (account != null) {
            return ResponseEntity.ok(new ApprovalDTO("OK", account.getApprovalCode()));
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<ApprovalDTO> debitAccount(@PathVariable String accountNumber,
                                                    @RequestBody CreditRequest debitRequest) {

        ApprovalDTO approvalDTO = bankAccountService.debitAccount(accountNumber, debitRequest.getAmount());
        if (approvalDTO != null) {
            return ResponseEntity.ok(approvalDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getAccount(@PathVariable String accountNumber) throws NotFoundException {
        try {
            BankAccountDTO account = bankAccountService.getAccountByAccountNumber(accountNumber);
            if (account != null) {
                BankAccountDTO response = new BankAccountDTO(
                        account.getAccountNumber(),
                        account.getOwner(),
                        account.getBalance(),
                        account.getCreateDate(),
                        account.getTransactions()
                );
                return ResponseEntity.ok(response);
            }
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

        return ResponseEntity.notFound().build();
    }
}

