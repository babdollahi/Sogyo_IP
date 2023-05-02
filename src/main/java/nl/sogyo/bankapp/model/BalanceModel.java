package nl.sogyo.bankapp.model;

import jakarta.persistence.Entity;
import java.util.Objects;

import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
@Entity
@Table(name="login")
public class BalanceModel {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "accountNumber")
    private int accountNumber;

    @Column(name = "pinNumber")
    private int pinNumber;

    @Column(name = "balance")
    private double balance;

    public BalanceModel() {}

    public BalanceModel(int id, int accountNumber, int pinNumber, double balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.pinNumber = pinNumber;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
