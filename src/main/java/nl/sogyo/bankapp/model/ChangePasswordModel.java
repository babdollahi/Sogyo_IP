package nl.sogyo.bankapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name="pinNumber")
public class ChangePasswordModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pinNumberId")
    private int pinNumberId;

    @Column(name = "pinNumber")
    private int pinNumber;
    @Column(name = "newPinNumber")
    private int newPinNumber;
    @Column(name = "dateOfProcess")
    private LocalDateTime dateOfProcess;

    public ChangePasswordModel() {
    }

    public ChangePasswordModel(int pinNumber, int newPinNumber) {
        this.pinNumber = pinNumber;
        this.newPinNumber = newPinNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public int getPinNumberId() {
        return pinNumberId;
    }

    public void setPinNumberId(int pinNumberId) {
        this.pinNumberId = pinNumberId;
    }

    public int getNewPinNumber() {
        return newPinNumber;
    }

    public void setNewPinNumber(int newPinNumber) {
        this.newPinNumber = newPinNumber;
    }

    public LocalDateTime getDateOfProcess() {
        return dateOfProcess;
    }

    public void setDateOfProcess(LocalDateTime dateOfProcess) {
        this.dateOfProcess = dateOfProcess;
    }
}
