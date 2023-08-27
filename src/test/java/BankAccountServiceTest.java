import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.eteration.simplebanking.dto.BankAccountDTO;
import com.eteration.simplebanking.entity.BankAccountEntity;
import com.eteration.simplebanking.repository.BankAccountRepository;
import com.eteration.simplebanking.service.impl.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BankAccountServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @InjectMocks
    private BankAccountService bankAccountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAccountByAccountNumber() {
        String accountNumber = "669-7788";
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setAccountNumber(accountNumber);
        bankAccountEntity.setOwner("Guney Caglayan");
        bankAccountEntity.setBalance(0.0);
        bankAccountEntity.setCreateDate(LocalDateTime.parse("2020-03-26T06:15:50.550"));

        when(bankAccountRepository.findByAccountNumber(accountNumber))
                .thenReturn(bankAccountEntity);

        BankAccountDTO bankAccountDTO = bankAccountService.getAccountByAccountNumber(accountNumber);

        assertNotNull(bankAccountDTO);
        assertEquals(accountNumber, bankAccountDTO.getAccountNumber());
        assertEquals("Guney Caglayan", bankAccountDTO.getOwner());
        assertEquals(0.0, bankAccountDTO.getBalance());
        assertEquals(LocalDateTime.parse("2020-03-26T06:15:50.550"), bankAccountDTO.getCreateDate());
    }

    // Diğer test metotları buraya eklenebilir
}
