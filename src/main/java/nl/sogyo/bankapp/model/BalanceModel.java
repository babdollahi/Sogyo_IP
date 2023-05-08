package nl.sogyo.bankapp.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name="login")
public class BalanceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountNumber")
    private int accountNumber;

    @Column(name = "pinNumber")
    private int pinNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountNumber", referencedColumnName = "accountNumber")
    private List<DepositModel> deposits = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountNumber", referencedColumnName = "accountNumber")
    private List<WithdrawalModel> withdrawals = new ArrayList<>();
    private double currentBalance;

    public BalanceModel() {
    }

    public BalanceModel(int accountNumber, int pinNumber) {
        this.accountNumber = accountNumber;
        this.pinNumber = pinNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public double getBalance() {
        for (DepositModel deposit : getDeposits()) {
            currentBalance += deposit.getAmount();
        }
        for (WithdrawalModel withdrawal : getWithdrawals()) {
            currentBalance -= withdrawal.getAmount();
        }
        return currentBalance;
    }


    public List<DepositModel> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<DepositModel> deposits) {
        this.deposits = deposits;
    }

    public void setBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public List<WithdrawalModel> getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(List<WithdrawalModel> withdrawals) {
        this.withdrawals = withdrawals;
    }
}
