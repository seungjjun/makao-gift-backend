package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.LoginFailed;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  public LoginService(UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User login(String userId, String password) {
    User user = userRepository.findByUserId(userId)
        .orElseThrow(() -> new LoginFailed());

    if(!user.authenticate(password, passwordEncoder)) {
      throw new LoginFailed();
    }
    return user;
  }
}
