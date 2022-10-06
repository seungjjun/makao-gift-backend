package kr.megaptera.makaogift.repositories;

import kr.megaptera.makaogift.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findAllBySender(String userId);

  Transaction save(Transaction transaction);
}
