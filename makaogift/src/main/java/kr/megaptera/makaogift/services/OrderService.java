package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.UserNotFound;
import kr.megaptera.makaogift.models.Transaction;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.repositories.TransactionRepository;
import kr.megaptera.makaogift.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderService {
  private final UserRepository userRepository;

  private final TransactionRepository transactionRepository;

  public OrderService(UserRepository userRepository, TransactionRepository transactionRepository) {
    this.userRepository = userRepository;
    this.transactionRepository = transactionRepository;
  }

  public Long order(String from, Long price, String receiver, String manufacturer,
                    String productName, String option, int productNumber,
                    String address, String message, String image) {
    User user = userRepository.findByUserId(from).orElseThrow(UserNotFound::new);

    user.pay(price);

    Transaction order = new Transaction(
        from, receiver, manufacturer, productName, option,
        productNumber, price, address, message, image
    );

    transactionRepository.save(order);
    return user.amount();
  }
}
