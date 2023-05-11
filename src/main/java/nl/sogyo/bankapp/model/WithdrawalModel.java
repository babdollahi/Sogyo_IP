package nl.sogyo.bankapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="withdrawal")
public class WithdrawalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int withdrawalId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "dateOfProcess")
    private LocalDateTime dateOfProcess;

    public WithdrawalModel() {
    }

    public WithdrawalModel(double amount, LocalDateTime dateOfProcess) {
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
