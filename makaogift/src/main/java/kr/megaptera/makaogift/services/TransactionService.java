package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.TransactionNotFound;
import kr.megaptera.makaogift.models.Transaction;
import kr.megaptera.makaogift.repositories.TransactionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactionService {
  private final TransactionRepository transactionRepository;
  private Pageable pageable;

  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public List<Transaction> list(String userId, int page) {
    Sort sort = Sort.by("id");
    pageable = PageRequest.of(page - 1, 8, sort);

    List<Transaction> transactions =
        transactionRepository.findAllBySender(userId, pageable);

    return transactions;
  }

  public Transaction findTransaction(Long id) {
    return transactionRepository.findById(id)
        .orElseThrow(TransactionNotFound::new);
  }

  public Long transactions() {
    return transactionRepository.findAll(pageable).getTotalElements();
  }
}
