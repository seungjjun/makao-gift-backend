package kr.megaptera.makaogift.utils;

import kr.megaptera.makaogift.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {

  JwtUtil jwtUtil;
  static private String SECRET = "SECRET";

  @BeforeEach
  void setUp() {
    jwtUtil = new JwtUtil(SECRET);
  }

  @Test
  void encodeAndDecode() {
    User user = new User("jel1y", "Qwe1234!", "Tester", 500L);

    String originalId = user.userId();

    String token = jwtUtil.encode(originalId);

    String userId = jwtUtil.decode(token);

    assertThat(userId).isEqualTo(originalId);
  }

  @Test
  void decodeError() {
    String userId = jwtUtil.decode("xxx");
  }
}
