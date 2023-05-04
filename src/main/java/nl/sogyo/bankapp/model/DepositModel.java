package nl.sogyo.bankapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="deposit")
public class DepositModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "depositId")
    private int depositId;

    @Column(name = "amount")
    private double amount;
    @Column(name = "dateOfProcess")
    private LocalDateTime dateOfProcess;

    public DepositModel() {
    }
    public DepositModel(double amount, LocalDateTime dateOfProcess) {
        this.amount = amount;
        this.dateOfProcess = dateOfProcess;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateOfProcess() {
        return dateOfProcess;
    }

    public void setDateOfProcess(LocalDateTime dateOfProcess) {
        this.dateOfProcess = dateOfProcess;
    }
}
