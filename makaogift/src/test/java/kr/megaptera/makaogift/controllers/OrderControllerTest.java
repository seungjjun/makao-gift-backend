package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.models.Transaction;
import kr.megaptera.makaogift.repositories.TransactionRepository;
import kr.megaptera.makaogift.services.OrderService;
import kr.megaptera.makaogift.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@ActiveProfiles("test")
class OrderControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OrderService orderService;

  @MockBean
  private TransactionService transactionService;

  @MockBean
  private TransactionRepository transactionRepository;

  @Test
  void list() throws Exception {
    Transaction transaction = mock(Transaction.class);

    String userId = "jel1y";

    given(transactionService.list(userId, 1)).willReturn(List.of(transaction));

    mockMvc.perform(MockMvcRequestBuilders.get("/orders"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("\"transactions\":[")
        ));
  }

  @Test
  void detail() throws Exception {
    Transaction transaction =
        new Transaction(1L,"jel1y", "Tester", 2, 20_000L,
            "Lotte", "jelly", "good", "seoul", "gift", LocalDateTime.now());

    given(transactionService.findTransaction(1L)).willReturn(transaction);

    mockMvc.perform(MockMvcRequestBuilders.get("/orders/1"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("\"id\":1")
        ));
  }

  @Test
  void notFoundTransaction() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/order/10000"))
        .andExpect(status().isNotFound());
  }

  @Test
  void order() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/order")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"id\":1," +
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

    verify(orderService).order("jel1y", 20_000L, "Tester", "Lotte", "jelly", "good",
        2, "seoul", "gift");
  }
}
