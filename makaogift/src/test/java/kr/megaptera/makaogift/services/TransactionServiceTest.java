package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.models.Transaction;
import kr.megaptera.makaogift.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TransactionServiceTest {
  @SpyBean
  TransactionService transactionService;

  @MockBean
  TransactionRepository transactionRepository;

  @BeforeEach
  void setup() {
    transactionRepository = mock(TransactionRepository.class);

    transactionService = new TransactionService(transactionRepository);
  }

  @Test
  void list() {
    String userId = "jel1y";

    Transaction transaction = new Transaction();

    given(transactionRepository.findAllBySender(any(), any(Pageable.class)))
        .willReturn(List.of(transaction));

    List<Transaction> transactions = transactionService.list(userId, 2);

    assertThat(transactions).hasSize(1);
  }
}
