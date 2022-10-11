package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.exceptions.LoginFailed;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.services.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
class SessionControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private LoginService loginService;

  @BeforeEach
  void setUp() {
    User user = User.fake();

    given(loginService.login("jel1y", "Qwe1234!")).willReturn(user);
    given(loginService.login("jel1y", "xxx")).willThrow(new LoginFailed());
    given(loginService.login("!@#$", "Qwe1234!")).willThrow(new LoginFailed());
  }

  @Test
  void loginSuccess() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"userId\":\"jel1y\"," +
                "\"password\":\"Qwe1234!\"" +
                " }"))
        .andExpect(status().isCreated());
  }

  @Test
  void loginFailedWithIncorrectUserId() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"userId\":\"!@#$\"," +
                "\"password\":\"Qwe1234!\"" +
                " }"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(
            containsString("\"code\":1001")
        ));
  }

  @Test
  void loginFailedWithIncorrectPassword() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"userId\":\"jel1y\"," +
                "\"password\":\"xxx\"" +
                " }"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(
            containsString("\"code\":1001")
        ));
  }

  @Test
  void loginWithBlankId() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"userId\":\"\"," +
                "\"password\":\"Qwe1234!\"" +
                " }"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(
            containsString("아이디를 입력해주세요")
        ));
  }

  @Test
  void loginWithBlankPassword() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"userId\":\"jel1y\"," +
                "\"password\":\"\"" +
                " }"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(
            containsString("비밀번호를 입력해주세요")
        ));
  }
}
