package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.dtos.UserRegistrationDto;
import kr.megaptera.makaogift.exceptions.UserNotFound;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.services.UserService;
import kr.megaptera.makaogift.utils.JwtUtil;
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

  @SpyBean
  private JwtUtil jwtUtil;

  private String name;
  private String userId;
  private String password;
  private String confirmPassword;

  private User user;

  private String token;

  @BeforeEach
  void setup() {
    name = "노승준";
    userId = "jel1y";
    password = "Qwe1234!";
    confirmPassword = "Qwe1234!";

    user = new User("jel1y", "Qwe1234!", "노승준", 50_000L);
    user.changePassword(password, passwordEncoder);

    token = jwtUtil.encode(userId);
  }

  @Test
  void user() throws Exception {
    given(userService.detail(user.userId())).willReturn(user);

    mockMvc.perform(MockMvcRequestBuilders.get("/")
            .header("Authorization", "Bearer " + token))
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

    given(userService.register(name, userId, password)).willReturn(user);

    mockMvc.perform(MockMvcRequestBuilders.post("/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"노승준\"," +
                "\"userId\":\"jel1y\"," +
                "\"password\":\"Qwe1234!\"," +
                "\"confirmPassword\":\"Qwe1234!\"" +
                "}"))
        .andExpect(status().isCreated());
  }

  @Test
  void signupWithBlankName() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"userId\":\"jel1y\"," +
                "\"password\":\"Qwe1234!\"," +
                "\"confirmPassword\":\"Qwe1234!\"" +
                "}"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(
            containsString("이름을 입력해주세요")
        ));
  }

  @Test
  void signupWithIncorrectName() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"Test\"," +
                "\"userId\":\"jel1y\"," +
                "\"password\":\"Qwe1234!\"," +
                "\"confirmPassword\":\"Qwe1234!\"" +
                "}"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(
            containsString("이름을 다시 확인해주세요")
        ));
  }

  @Test
  void signupWithBlankUserId() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"노승준\"," +
                "\"password\":\"Qwe1234!\"," +
                "\"confirmPassword\":\"Qwe1234!\"" +
                "}"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(
            containsString("아이디를 입력해주세요")
        ));
  }

  @Test
  void signupWithIncorrectId() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"노승준\"," +
                "\"userId\":\"노승준\"," +
                "\"password\":\"Qwe1234!\"," +
                "\"confirmPassword\":\"Qwe1234!\"" +
                "}"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(
            containsString("아이디를 다시 확인해주세요")
        ));
  }

  @Test
  void signupWithBlankPassword() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"노승준\"," +
                "\"userId\":\"jel1y\"," +
                "\"confirmPassword\":\"Qwe1234!\"" +
                "}"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(
            containsString("비밀번호를 입력해주세요")
        ));
  }

  @Test
  void signupWithIncorrectPassword() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"노승준\"," +
                "\"userId\":\"jel1y\"," +
                "\"password\":\"password\"," +
                "\"confirmPassword\":\"Qwe1234!\"" +
                "}"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(
            containsString("비밀번호를 다시 확인해주세요")
        ));
  }

  @Test
  void signupWithBlankConfirmPassword() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"노승준\"," +
                "\"userId\":\"jel1y\"," +
                "\"password\":\"Qwe1234!\"," +
                "\"confirmPassword\":\"\"" +
                "}"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(
            containsString("비밀번호를 입력해주세요")
        ));
  }
}
