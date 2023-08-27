import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.eteration.simplebanking.dto.ApprovalDTO;
import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.entity.BankAccountEntity;
import com.eteration.simplebanking.entity.TransactionEntity;
import com.eteration.simplebanking.enums.TransactionTypeEnums;
import com.eteration.simplebanking.repository.BankAccountRepository;
import com.eteration.simplebanking.repository.TransactionRepository;
import com.eteration.simplebanking.service.impl.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Optional;

public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private BankAccountRepository bankAccountRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCreditTransaction() {
        // Mock verileri
        String accountNumber = "669-7788";
        double amount = 100.0;

        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setAccountNumber(accountNumber);
        bankAccountEntity.setBalance(200.0);


        // Banka hesabını mockla
        when(bankAccountRepository.findByAccountNumber(accountNumber))
                .thenReturn(bankAccountEntity);

        // İşlemi oluştur
        ApprovalDTO approvalDTO = transactionService.creditAccount(accountNumber, amount);


        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setType(TransactionTypeEnums.DEPOSIT);
        transactionEntity.setBankAccount(bankAccountEntity);
        transactionEntity.setApprovalCode(approvalDTO.getApprovalCode());
        transactionEntity.setCreateDate(LocalDateTime.now());

        // Doğrulamalar
        assertNotNull(approvalDTO);

        // Banka hesabı bakiyesini kontrol et
        assertEquals(200.0 + amount, bankAccountEntity.getBalance());

        // İşlem kaydedildiğini kontrol et
        verify(transactionRepository).save(transactionEntity);
    }

    @Test
    public void testCreateDebitTransaction() {
        // Mock verileri
        String accountNumber = "669-7788";
        double amount = 100.0;

        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setAccountNumber(accountNumber);
        bankAccountEntity.setBalance(200.0);


        // Banka hesabını mockla
        when(bankAccountRepository.findByAccountNumber(accountNumber))
                .thenReturn(bankAccountEntity);

        // İşlemi oluştur
        ApprovalDTO approvalDTO = transactionService.creditAccount(accountNumber, amount);


        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setType(TransactionTypeEnums.WITHDRAWAL);
        transactionEntity.setBankAccount(bankAccountEntity);
        transactionEntity.setApprovalCode(approvalDTO.getApprovalCode());
        transactionEntity.setCreateDate(LocalDateTime.now());

        // Doğrulamalar
        assertNotNull(approvalDTO);

        // Banka hesabı bakiyesini kontrol et
        assertEquals(200.0 - amount, bankAccountEntity.getBalance());

        // İşlem kaydedildiğini kontrol et
        verify(transactionRepository).save(transactionEntity);
    }
}
