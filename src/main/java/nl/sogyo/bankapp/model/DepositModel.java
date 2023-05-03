package nl.sogyo.bankapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "userTransaction")
public class DepositModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "accountNumber")
    private int accountNumber;

    @Column(name = "dateOfProcess")
    private LocalDateTime dateOfProcess;

    @Column(name = "amount", precision = 5, scale = 2)
    private double amount;

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

    public LocalDateTime getDateOfProcess() {
        return dateOfProcess;
    }

    public void setDateOfProcess(LocalDateTime dateOfProcess) {
        this.dateOfProcess = LocalDateTime.from(dateOfProcess);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
