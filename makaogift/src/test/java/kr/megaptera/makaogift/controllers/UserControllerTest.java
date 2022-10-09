package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.exceptions.UserNotFound;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

  @Test
  void user() throws Exception {
    given(userService.detail(any())).willReturn(User.fake());

    mockMvc.perform(MockMvcRequestBuilders.get("/user/me"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("\"amount\":50000")
        ));
  }

  @Test
  void userNotFound() throws Exception {
    given(userService.detail(any())).willThrow(new UserNotFound());

    mockMvc.perform(MockMvcRequestBuilders.get("/user/me"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void signup() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"name\":\"노승준\"," +
                "\"userId\":\"jel1y\"," +
                "\"password\":\"Qwe1234!\"," +
                "\"confirmPassword\":\"Qwe1234!\"" +
                "}"))
        .andExpect(status().isCreated())
        .andExpect(content().string(
            containsString("name:")
        ));
  }
}
