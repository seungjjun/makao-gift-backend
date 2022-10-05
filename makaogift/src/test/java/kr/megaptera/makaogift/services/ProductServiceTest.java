package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProductServiceTest {
  @SpyBean
  private ProductService productService;

  @MockBean
  private ProductRepository productRepository;

  @SpyBean
  private Pageable pageable;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @BeforeEach
  void setUp() {
    productRepository = mock(ProductRepository.class);

    productService = new ProductService(productRepository);
  }

  @Test
  void list() {
    Product product = mock(Product.class);

    Sort sort = Sort.by("id").descending();

    pageable = PageRequest.of(1, 8, sort);

    List<Product> products = new ArrayList<>();
    products.add(product);

    Page<Product> page = new PageImpl<>(products);

    assertThat(page).hasSize(1);

    given(productRepository.findAll(pageable)).willReturn(page);

    Page<Product> pageProducts = productService.list(2);

    assertThat(pageProducts).hasSize(1);
  }

  @Test
  void allProduct() {
    Product product = mock(Product.class);

    List<Product> products = new ArrayList<>();
    products.add(product);

    Page<Product> page = new PageImpl<>(products);

    given(productRepository.findAll(pageable)).willReturn(page);

    Long productNumber = productService.allProduct();

    assertThat(productNumber).isEqualTo(1);
  }

  @Test
  void findProduct() {
    Product product = new Product(1L, "manufacturer", "name", "option", 100L);

    productRepository.save(product);

    verify(productRepository).save(product);

    Product foundedProduct = productService.findProduct(1L);

    assertThat(foundedProduct.getName()).isEqualTo("name");
  }
}
