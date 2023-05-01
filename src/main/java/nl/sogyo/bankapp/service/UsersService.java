package nl.sogyo.bankapp.service;

import nl.sogyo.bankapp.model.BalanceModel;
import nl.sogyo.bankapp.repository.AccountRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private AccountRepository accountRepository;

    public UsersService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<BalanceModel> checkLogin(int accountNumber, int pinNumber){
        return  accountRepository.findByAccountNumberAndPinNumber(accountNumber,pinNumber);
    }

}
