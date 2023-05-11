package nl.sogyo.bankapp.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import nl.sogyo.bankapp.model.BalanceModel;
import nl.sogyo.bankapp.repository.AccountRepository;
import nl.sogyo.bankapp.service.UsersService;

import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

class ControllerTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private UsersService usersService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCheckLogin() {
        // create a mock BalanceModel object
        BalanceModel balanceModel = new BalanceModel(123123, 123);

        // configure the mock accountRepository to return the mock BalanceModel object
        when(accountRepository.findByAccountNumberAndPinNumber(123123, 123)).thenReturn(Optional.of(balanceModel));

        // call the checkLogin method with the mock account number and pin number
        Optional<BalanceModel> result = usersService.checkLogin(123123, 123);

        // verify that the accountRepository was called with the correct account number and pin number
        verify(accountRepository).findByAccountNumberAndPinNumber(123123, 123);

        // verify that the checkLogin method returned the mock BalanceModel object
        assertTrue(result.isPresent());
        assertEquals(balanceModel, result.get());
    }

}
