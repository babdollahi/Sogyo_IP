package nl.sogyo.bankapp.repository;

import nl.sogyo.bankapp.model.DepositModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<DepositModel, Integer> {

}