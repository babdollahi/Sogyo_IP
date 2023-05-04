package nl.sogyo.bankapp.repository;

import nl.sogyo.bankapp.model.BalanceModel;

import nl.sogyo.bankapp.model.DepositModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<BalanceModel, Integer> {
    Optional<BalanceModel> findByAccountNumberAndPinNumber(int accountNumber, int pinNumber);
    Optional<BalanceModel> findByAccountNumber(int accountNumber);

    DepositModel save(DepositModel depositModel);
    
}
