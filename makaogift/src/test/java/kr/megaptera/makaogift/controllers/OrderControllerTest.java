package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@ActiveProfiles("test")
class OrderControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OrderService orderService;

  @Test
  void order() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/order")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"sender\":\"jel1y\"," +
                "\"receiver\":\"Tester\"," +
                "\"productName\":\"jelly\"," +
                "\"productNumber\":2," +
                "\"manufacturer\":\"Lotte\"," +
                "\"price\":20000," +
                "\"option\":\"good\"," +
                "\"address\":\"seoul\"," +
                "\"message\":\"gift\"" +
                "}"))
        .andExpect(status().isCreated());

    verify(orderService).order("jel1y", 20_000L);
  }
}
