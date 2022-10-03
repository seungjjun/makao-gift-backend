package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.UserNotFound;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User detail(String userId) {
    User user = userRepository.findByUserId(userId)
        .orElseThrow(() -> new UserNotFound());

    return user;
  }
}
