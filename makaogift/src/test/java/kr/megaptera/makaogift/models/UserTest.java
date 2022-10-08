package kr.megaptera.makaogift.models;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
  @Test
  void pay() {
    User user = new User(1L, "jel1y", "Qwe1234!", "Pikachu", 50_000L);

    user.pay(20_000L);

    assertThat(user.amount()).isEqualTo(30_000L);
  }

  @Test
  void authenticate() {
    User user = User.fake();

    PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

    user.changePassword("password", passwordEncoder);

    assertThat(user.authenticate("password", passwordEncoder)).isTrue();
    assertThat(user.authenticate("xxx", passwordEncoder)).isFalse();
  }
}
