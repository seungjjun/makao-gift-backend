package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.models.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {
  @Test
  void user() {
    UserService userService = new UserService();

    User user = userService.detail("jel1y");

    assertThat(user.id()).isEqualTo("jel1y");
  }

}