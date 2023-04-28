package nl.sogyo.bankapp.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name="login")
public class BalanceModel {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "accountNumber")
    private String accountNumber;

    @Column(name = "pinNumber")
    private String pinNumber;

    @Column(name = "balance")
    private double balance;

    public BalanceModel() {}

    public BalanceModel(int id, String accountNumber, String pinNumber, double balance) {
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

    public String getUsername() {
        return accountNumber;
    }

    public void setUsername(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return pinNumber;
    }

    public void setPassword(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
