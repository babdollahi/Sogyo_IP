package nl.sogyo.bankapp.repository;

import nl.sogyo.bankapp.model.BalanceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<BalanceModel, Integer> {
    Optional<BalanceModel> findByUserAccountNumber(int AccountNumber);
    void updateBalance(int AccountNumber, double newBalance);
}
