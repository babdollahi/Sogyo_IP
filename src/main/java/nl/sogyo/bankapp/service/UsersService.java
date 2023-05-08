package nl.sogyo.bankapp.service;

import nl.sogyo.bankapp.model.BalanceModel;
import nl.sogyo.bankapp.model.DepositModel;
import nl.sogyo.bankapp.model.WithdrawalModel;
import nl.sogyo.bankapp.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsersService {

    private AccountRepository accountRepository;

    public UsersService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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

    public double loanCalculation(int accountNumber, double amount) {
        Optional<BalanceModel> balanceModelOptional = accountRepository.findById(accountNumber);
        if (balanceModelOptional.isPresent()) {
            BalanceModel balanceModel = balanceModelOptional.get();
            double newBalance = balanceModel.getBalance() - amount;
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
//    private void loanActionPerformed(java.awt.event.ActionEvent evt) {
//        double yearlyInterestRate = Double.parseDouble(yearlyInterestRateText.getText());
//        double monthlyInterestRate = (yearlyInterestRate / 12) / 100;
//        int years = Integer.parseInt(yearsText.getText());
//        double loanAmount = Double.parseDouble(amountOfLoanText.getText());
//        double monthlyPayment = loanAmount * monthlyInterestRate /
//                (1 - 1 /Math.pow(1+ monthlyInterestRate, years *12));
//
//        String imonthlyPayment = Double.toString(monthlyPayment);
//        imonthlyPayment = String.format("€%.2f", monthlyPayment);
//        monthlyPaymentText.setText(imonthlyPayment);
//
//        double totalPayment = monthlyPayment * years * 12;
//        String itotalPayment = String.format("€%.2f", totalPayment);
//        totalPaymentText.setText(itotalPayment);
//    }
//



}
