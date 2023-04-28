package nl.sogyo.bankapp.service;

import nl.sogyo.bankapp.model.UsersModel;
import nl.sogyo.bankapp.model.BalanceModel;

import nl.sogyo.bankapp.repository.UsersRepository;
import nl.sogyo.bankapp.repository.AccountRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUser(String login, String password, String email) {
        if (login == null && password == null) {
            return null;
        } else {
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            usersRepository.save(usersModel);
            return usersRepository.save(usersModel);
        }
    }


    public UsersModel authentication(String login, String password){
        return usersRepository.findByLoginAndPassword(login, password).orElse(null);
    }


    private AccountRepository accountRepository;

    public UsersService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public BalanceModel getUserBalance(int accountNumber) {
        Optional<BalanceModel> balanceModel = accountRepository.findByUserAccountNumber(accountNumber);
        return balanceModel.getBalance();
    }

}
