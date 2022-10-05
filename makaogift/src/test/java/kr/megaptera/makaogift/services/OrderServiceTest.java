package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class OrderServiceTest {
  private OrderService orderService;

  private UserRepository userRepository;

  User user;

  @BeforeEach
  void setUp() {
    user = new User(1L, "jel1y", "Pikachu", 50_000L);

    userRepository = mock(UserRepository.class);

    orderService = new OrderService(userRepository);

    given(userRepository.findByUserId("jel1y")).willReturn(Optional.of(user));
  }

  @Test
  void order() {
    orderService.order("jel1y", 20_000L);

    assertThat(user.amount()).isEqualTo(30_000L);
  }

  @Test
  void orderWithTooMuchPrice() {
    orderService.order("jel1y", 100_000L);

    assertThat(user.amount()).isEqualTo(50_000L);
  }
}
