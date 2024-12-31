package dev.rossy.transactionms.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
//    List<Transaction> findByCustomerId(String customerId);

    Optional<Transaction> findByCustomerId(Integer customerId);
}
