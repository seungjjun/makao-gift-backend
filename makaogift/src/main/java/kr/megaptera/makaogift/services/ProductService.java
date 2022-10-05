package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.ProductNotFound;
import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductService {
  private final ProductRepository productRepository;
  private Pageable pageable;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Page<Product> list(int page) {
    Sort sort = Sort.by("id");
    pageable = PageRequest.of(page - 1, 8, sort);

    return productRepository.findAll(pageable);
  }

  public Long allProduct() {
    return productRepository.findAll(pageable).getTotalElements();
  }

  public Product findProduct(Long id) {
    return productRepository.findById(id)
        .orElseThrow(ProductNotFound::new);
  }
}
