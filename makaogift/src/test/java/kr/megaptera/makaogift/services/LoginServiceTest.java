package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.LoginFailed;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LoginServiceTest {
  private LoginService loginService;

  private UserRepository userRepository;

  private PasswordEncoder passwordEncoder;

  @BeforeEach
  void setUp() {
    userRepository = mock(UserRepository.class);

    passwordEncoder = new Argon2PasswordEncoder();

    loginService = new LoginService(userRepository, passwordEncoder);
  }

  @Test
  void loginSuccess() {
    String userId = "jel1y";

    User user = User.fake();

    user.changePassword("password", passwordEncoder);
    given(userRepository.findByUserId(userId))
        .willReturn(Optional.of(user));

    User foundedUser = loginService.login("jel1y", "password");

    assertThat(foundedUser.userId()).isEqualTo(userId);
  }

  @Test
  void loginFailedWithIncorrectUserId() {
    assertThrows(LoginFailed.class, () -> {
      loginService.login("xxxx", "Qwe1234!");
    });
  }

  @Test
  void loginFailedWithIncorrectPassword() {
    assertThrows(LoginFailed.class, () -> {
      loginService.login("jel1y", "xxx");
    });
  }
}
