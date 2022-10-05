package kr.megaptera.makaogift.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {
  @Test
  void creation() {
    Product product = new Product(2L, "롯데", "감자칩", "어제 캔 감자로 만든", 3_000L);

    assertThat(product.getManufacturer()).isEqualTo("롯데");
    assertThat(product.getName()).isEqualTo("감자칩");
    assertThat(product.getPrice()).isEqualTo(3_000L);
  }
}
