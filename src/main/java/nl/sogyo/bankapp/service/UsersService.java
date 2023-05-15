package nl.sogyo.bankapp.service;

import nl.sogyo.bankapp.model.*;
import nl.sogyo.bankapp.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UsersService {
    private AccountRepository accountRepository;

    public UsersService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void registerUser(int accountNumber, int pinNumber, String email) {
        BalanceModel balanceModel = new BalanceModel();
        balanceModel.setAccountNumber(accountNumber);
        balanceModel.setPinNumber(pinNumber);
        balanceModel.setEmail(email);
        accountRepository.save(balanceModel);
    }


    public Optional<BalanceModel> checkLogin(int accountNumber, int pinNumber) {
        return accountRepository.findByAccountNumberAndPinNumber(accountNumber, pinNumber);
    }

    public double addDeposit(int accountNumber, double amount) {
        Optional<BalanceModel> balanceModelOptional = accountRepository.findById(accountNumber);
        if (balanceModelOptional.isPresent()) {
            BalanceModel balanceModel = balanceModelOptional.get();
            LocalDateTime now = LocalDateTime.now();
            DepositModel depositModel = new DepositModel();
            balanceModel.getDeposits().add(depositModel);
            depositModel.setAmount(amount);
            depositModel.setDateOfProcess(now);
            accountRepository.save(balanceModel);
            return balanceModel.getBalance();
        }
        return 0;
    }

    public double withdrawal(int accountNumber, double amount) throws InsufficientFundsException {
        Optional<BalanceModel> balanceModelOptional = accountRepository.findById(accountNumber);
        if (balanceModelOptional.isPresent()) {
            BalanceModel balanceModel = balanceModelOptional.get();
            double newBalance = balanceModel.getBalance() - amount;
            if (newBalance < 0) {
                throw new InsufficientFundsException("Insufficient funds");
            }
            LocalDateTime now = LocalDateTime.now();
            WithdrawalModel withdrawalModel = new WithdrawalModel();
            balanceModel.getWithdrawals().add(withdrawalModel);
            withdrawalModel.setAmount(amount);
            withdrawalModel.setDateOfProcess(now);
            balanceModel.setBalance(newBalance);
            accountRepository.save(balanceModel);
            return newBalance;
        }
        return 0;
    }

    public Map<String, Double> loanCalculation(int accountNumber, double yearlyInterestRate, int years, double loanAmount) {
        Optional<BalanceModel> balanceModelOptional = accountRepository.findById(accountNumber);
        if (balanceModelOptional.isPresent()) {
            BalanceModel balanceModel = balanceModelOptional.get();
            LoanModel loanModel = new LoanModel();
            balanceModel.getLoans().add(loanModel);

            loanModel.setYearlyInterestRate(yearlyInterestRate);
            loanModel.setLoanAmount(loanAmount);
            loanModel.setYears(years);

            double monthlyInterestRate = (yearlyInterestRate / 12) / 100;
            double monthlyPayment = loanAmount * monthlyInterestRate / (1 - 1 / Math.pow(1 + monthlyInterestRate, years * 12));
            loanModel.setMonthlyPayment(monthlyPayment);

            double totalPayment = monthlyPayment * years * 12;
            loanModel.setTotalPayment(totalPayment);

            accountRepository.save(balanceModel);
            Map<String, Double> repayments = new HashMap<>();
            repayments.put("monthlyPayment", Math.round(monthlyPayment * 100) / 100.0);
            repayments.put("totalPayment", Math.round(totalPayment * 100) / 100.0);

            return repayments;
        }
        return null;
    }


    public void saveRegistration(BalanceModel registerRequest) {
        accountRepository.save(registerRequest);
    }

    public void changePassword(int pinNumber, int accountNumber, int newPinNumber, int confirmNewPinNumber) {
        Optional<BalanceModel> balanceModelOptional = accountRepository.findByAccountNumberAndPinNumber(accountNumber, pinNumber);

        if (balanceModelOptional.isPresent()) {
            BalanceModel balanceModel = balanceModelOptional.get();
            ChangePasswordModel changePasswordModel = new ChangePasswordModel();
            balanceModel.getChangedPassword().add(changePasswordModel);

            LocalDateTime now = LocalDateTime.now();

            changePasswordModel.setNewPinNumber(newPinNumber);
            changePasswordModel.setDateOfProcess(now);

            if (newPinNumber == confirmNewPinNumber) {
                balanceModel.setPinNumber(newPinNumber);
                accountRepository.save(balanceModel);
            } else {
                // Handle case when newPinNumber and confirmNewPinNumber don't match
                throw new IllegalArgumentException("confirmPinMismatch");
            }
        } else {
            // Handle case when accountNumber and pinNumber combination is not found
            throw new IllegalArgumentException("InvalidPinAccount");
        }
    }


}

