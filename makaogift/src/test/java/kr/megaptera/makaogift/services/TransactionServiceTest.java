package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.models.Transaction;
import kr.megaptera.makaogift.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TransactionServiceTest {
  TransactionService transactionService;
  TransactionRepository transactionRepository;

  @BeforeEach
  void setup() {
    transactionRepository = mock(TransactionRepository.class);

    transactionService = new TransactionService(transactionRepository);
  }

  @Test
  void list() {
    String userId = "jel1y";

    Transaction transaction = mock(Transaction.class);

    given(transactionRepository.findAllBySender(userId)).willReturn(List.of(transaction));

    List<Transaction> transactions = transactionService.list(userId);

    assertThat(transactions).hasSize(1);
  }
}
