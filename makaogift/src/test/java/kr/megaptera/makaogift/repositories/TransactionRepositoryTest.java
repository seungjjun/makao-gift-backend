package kr.megaptera.makaogift.repositories;

import kr.megaptera.makaogift.models.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class TransactionRepositoryTest {
  @Autowired
  private TransactionRepository transactionRepository;

  @Test
  void save() {
    Transaction transaction = new Transaction(
       "jel1y", "Pikachu", "Lotte", "snack",
        "delicious", 1, 10_000L, "seoul", "gift"
    );

    transactionRepository.save(transaction);
  }
}
