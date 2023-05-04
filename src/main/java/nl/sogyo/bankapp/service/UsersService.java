package nl.sogyo.bankapp.service;

import nl.sogyo.bankapp.model.BalanceModel;
import nl.sogyo.bankapp.model.DepositModel;
import nl.sogyo.bankapp.repository.AccountRepository;
import nl.sogyo.bankapp.repository.DepositRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsersService {

    private AccountRepository accountRepository;
//    private DepositRepository depositRepository;

//    public UsersService(AccountRepository accountRepository, DepositRepository depositRepository) {
//        this.accountRepository = accountRepository;
//        this.depositRepository = depositRepository;
//
//    }
    public UsersService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;//
    }


    public Optional<BalanceModel> checkLogin(int accountNumber, int pinNumber) {
        return accountRepository.findByAccountNumberAndPinNumber(accountNumber, pinNumber);
    }

//    public double addDeposit(int accountNumber, double amount) {
//        Optional<BalanceModel> balanceModelOptional = accountRepository.findByAccountNumber(accountNumber);
//        if (balanceModelOptional.isPresent()) {
//            BalanceModel balanceModel = balanceModelOptional.get();
//            double currentBalance = balanceModel.getBalance();
//            double updatedBalance = currentBalance + amount;
//            balanceModel.setBalance(updatedBalance);
//            LocalDateTime now = LocalDateTime.now();
//            DepositModel depositModel = new DepositModel();
////            depositModel.setAccountNumber(accountNumber);
//            depositModel.setAmount(amount);
//            depositModel.setDateOfProcess(now);
//            accountRepository.save(balanceModel);
////            depositRepository.save(depositModel);
//            return balanceModel.getBalance();
//        }
//        return 0;
//    }

    public double addDeposit(int accountNumber, double amount) {
        Optional<BalanceModel> balanceModelOptional = accountRepository.findById(accountNumber);
        if (balanceModelOptional.isPresent()) {
            BalanceModel balanceModel = balanceModelOptional.get();
            double currentBalance = balanceModel.getBalance();
            double updatedBalance = currentBalance + amount;
            balanceModel.setBalance(updatedBalance);
            LocalDateTime now = LocalDateTime.now();
            DepositModel depositModel = new DepositModel();
            balanceModel.getDeposits().add(depositModel);
            depositModel.setAmount(amount);
            depositModel.setDateOfProcess(now);
//            depositRepository.save(depositModel);
            accountRepository.save(balanceModel);
            return balanceModel.getBalance();
        }
        return 0;
    }

}
