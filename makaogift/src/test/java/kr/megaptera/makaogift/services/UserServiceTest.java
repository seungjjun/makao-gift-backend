package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.dtos.UserRegistrationDto;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UserServiceTest {
  private UserService userService;

  private UserRepository userRepository;

  private PasswordEncoder passwordEncoder;

  @BeforeEach
  void setUp() {
    userRepository = mock(UserRepository.class);

    passwordEncoder = new Argon2PasswordEncoder();

    given(userRepository.findByUserId(any())).willReturn(Optional.of(User.fake()));

    userService = new UserService(userRepository, passwordEncoder);
  }

  @Test
  void user() {
    User user = userService.detail("jel1y");

    verify(userRepository).findByUserId("jel1y");

    assertThat(user.userId()).isEqualTo("jel1y");
    assertThat(user.amount()).isEqualTo(50_000L);
  }

  @Test
  void register() {
    UserRegistrationDto userRegistrationDto =
        new UserRegistrationDto("노승준", "jel1y", "Qwe1234!", "Qwe1234!");

    User user = userService.register(userRegistrationDto);

    verify(userRepository).save(user);
  }
}
