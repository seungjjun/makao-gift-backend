package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.UserNotFound;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
  private final UserRepository userRepository;

  public OrderService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Long order(String from,Long price) {
    User user = userRepository.findByUserId(from).orElseThrow(UserNotFound::new);

    user.pay(price);
    return user.amount();
  }
}
