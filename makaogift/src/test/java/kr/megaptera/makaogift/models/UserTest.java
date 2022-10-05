package kr.megaptera.makaogift.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
  @Test
  void pay() {
    User user = new User(1L, "jel1y", "Pikachu", 50_000L);

    user.pay(20_000L);

    assertThat(user.amount()).isEqualTo(30_000L);
  }
}
