package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.dtos.UserRegistrationDto;
import kr.megaptera.makaogift.exceptions.UserNotFound;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @SpyBean
  private PasswordEncoder passwordEncoder;

  private String name;
  private String userId;
  private String password;
  private String confirmPassword;

  private User user;

  @BeforeEach
  void setup() {
    name = "노승준";
    userId = "jel1y";
    password = "password";
    confirmPassword = "password";

    user = new User("jel1y", "password", "노승준", 50_000L);
    user.changePassword(password, passwordEncoder);
  }

  @Test
  void user() throws Exception {
    given(userService.detail(any())).willReturn(User.fake());

    mockMvc.perform(MockMvcRequestBuilders.get("/")
            .content(MediaType.APPLICATION_JSON_VALUE)
            .content("{" +
                "\"userId\":\"jel1y\"" +
                "}"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("\"amount\":50000")
        ));
  }

  @Test
  void userNotFound() throws Exception {
    given(userService.detail(any())).willThrow(new UserNotFound());

    mockMvc.perform(MockMvcRequestBuilders.get("/"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void signup() throws Exception {

    UserRegistrationDto userRegistrationDto =
        new UserRegistrationDto(name, userId, password, confirmPassword);

    given(userService.register(userRegistrationDto)).willReturn(user);

    mockMvc.perform(MockMvcRequestBuilders.post("/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"노승준\"," +
                "\"userId\":\"jel1y\"," +
                "\"password\":\"password\"," +
                "\"confirmPassword\":\"password\"" +
                "}"))
        .andExpect(status().isCreated())
        .andExpect(content().string(
            containsString("name:")
        ));
  }
}
