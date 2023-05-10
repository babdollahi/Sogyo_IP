package nl.sogyo.bankapp.model;

import jakarta.persistence.*;

@Entity
@Table(name="loan")
public class LoanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int loanId;

    @Column(name = "yearlyInterestRate")
    private double yearlyInterestRate;

    @Column(name = "years")
    private int years ;
    @Column(name = "loanAmount")
    private double loanAmount;

    @Column(name = "monthlyInterestRate")
    private double monthlyInterestRate;

    @Column(name = "monthlyPayment")
    private double monthlyPayment;

    @Column(name = "totalPayment")
    private double totalPayment;

    public LoanModel() {
    }

    public LoanModel(double yearlyInterestRate, int years, double loanAmount ) {
        this.yearlyInterestRate = yearlyInterestRate;
        this.years = years;
        this.loanAmount = loanAmount;
    }

    public double getYearlyInterestRate() {
        return yearlyInterestRate;
    }

    public void setYearlyInterestRate(double yearlyInterestRate) {
        this.yearlyInterestRate = yearlyInterestRate;
    }

    public double getMonthlyInterestRate() {
        return monthlyInterestRate;
    }

    public void setMonthlyInterestRate(double monthlyInterestRate) {
        this.monthlyInterestRate = monthlyInterestRate;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }
}
