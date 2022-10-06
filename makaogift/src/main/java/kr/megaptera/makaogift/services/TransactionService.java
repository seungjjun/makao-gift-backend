package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.TransactionNotFound;
import kr.megaptera.makaogift.models.Transaction;
import kr.megaptera.makaogift.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactionService {
  private final TransactionRepository transactionRepository;

  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public List<Transaction> list(String userId) {
    List<Transaction> transactions = transactionRepository.findAllBySender(userId);
    return transactions;
  }

  public Transaction findTransaction(Long id) {
    return transactionRepository.findById(id)
        .orElseThrow(TransactionNotFound::new);
  }
}
