package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductService productService;

  @BeforeEach
  void setUp() {
    String manufacturer = "orion";
    String name = "jelly";
    String option = "big size";
    Long price = 10_000L;

    Product product = new Product(1L, manufacturer, name, option, price);

    List<Product> products = new ArrayList<>();
    products.add(product);

    Page<Product> page = new PageImpl<>(products);

    given(productService.list(1)).willReturn(page);
    given(productService.findProduct(1L)).willReturn(product);
  }

  @Test
  void products() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/products"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("\"manufacturer\":\"orion\"")
        ));

    verify(productService).list(1);
  }

  @Test
  void detail() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("\"manufacturer\":\"orion\"")
        ));
  }
}
