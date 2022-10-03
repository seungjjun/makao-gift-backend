package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UserServiceTest {
  private UserService userService;
  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    userRepository = mock(UserRepository.class);

    given(userRepository.findByUserId(any())).willReturn(Optional.of(User.fake()));

    userService = new UserService(userRepository);
  }

  @Test
  void user() {
    User user = userService.detail("jel1y");

    verify(userRepository).findByUserId("jel1y");

    assertThat(user.userId()).isEqualTo("jel1y");
    assertThat(user.amount()).isEqualTo(50_000L);
  }
}
