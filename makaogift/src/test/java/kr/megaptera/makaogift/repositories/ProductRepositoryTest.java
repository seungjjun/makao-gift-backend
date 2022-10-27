package kr.megaptera.makaogift.repositories;

import kr.megaptera.makaogift.exceptions.ProductNotFound;
import kr.megaptera.makaogift.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ProductRepositoryTest {
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  void findAll() {
    String manufacturer = "orion";
    String name = "jelly";
    String option = "big size";
    Long price = 10_000L;

    jdbcTemplate.execute("DELETE FROM product");

    jdbcTemplate.update("" +
            "INSERT INTO PRODUCT(" +
            "   id, manufacturer, name, option, price" +
            ")" +
            " VALUES(1, ?, ?, ?, ?)",
        manufacturer, name, option, price);


    Sort sort = Sort.by("id").descending();
    Pageable pageable = PageRequest.of(0, 8, sort);
    Page<Product> products = productRepository.findAll(pageable);

    assertThat(products).hasSize(1);

  }

  @Test
  void findByProductId() {
    String manufacturer = "orion";
    String name = "jelly";
    String option = "big size";
    Long price = 10_000L;

    jdbcTemplate.execute("DELETE FROM product");

    jdbcTemplate.update("" +
            "INSERT INTO PRODUCT(" +
            "   id, manufacturer, name, option, price" +
            ")" +
            " VALUES(1, ?, ?, ?, ?)",
        manufacturer, name, option, price);

    Product product = productRepository.findById(1L).orElseThrow(ProductNotFound::new);

    assertThat(product.getName()).isEqualTo("jelly");
  }
}
