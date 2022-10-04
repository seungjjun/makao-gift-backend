package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductService productService;

  @Test
  void products() throws Exception {
    String manufacturer = "orion";
    String name = "jelly";
    String option = "big size";
    Long price = 10_000L;

    Product product = new Product(1L, manufacturer, name, option, price);

    List<Product> products = new ArrayList<>();
    products.add(product);

    Page<Product> page = new PageImpl<>(products);

    given(productService.list(1)).willReturn(page);

    mockMvc.perform(MockMvcRequestBuilders.get("/products")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"manufacturer\":\"" + manufacturer + "\"," +
                "\"name\":\"" + name + "\"," +
                "\"option\":\"" + option + "\"," +
                "\"price\":10000" +
                "}"))
        .andExpect(status().isOk());

    verify(productService).list(1);
  }
}
