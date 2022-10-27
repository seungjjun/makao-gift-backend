package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.dtos.UserRegistrationDto;
import kr.megaptera.makaogift.exceptions.ExistingUserId;
import kr.megaptera.makaogift.exceptions.UserNotFound;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User detail(String userId) {
    User user = userRepository.findByUserId(userId)
        .orElseThrow(() -> new UserNotFound());

    return user;
  }

  public User register(String name, String userId, String password) {
    if (!(userRepository.getByUserId(userId) == null)) {
      throw new ExistingUserId();
    }

    User user = new User(
        name,
        userId,
        password
        );

    user.changePassword(password, passwordEncoder);

    userRepository.save(user);

    return user;
  }
}
