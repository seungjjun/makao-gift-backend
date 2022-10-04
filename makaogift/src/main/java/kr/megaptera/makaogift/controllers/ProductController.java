package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.dtos.ProductDto;
import kr.megaptera.makaogift.dtos.ProductsDto;
import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("products")
  public ProductsDto products(
      @RequestParam(required = false, defaultValue = "1") Integer page
  ) {
    List<ProductDto> productDto = productService.list(page)
        .stream()
        .map(Product::toDto)
        .collect(Collectors.toList());

    Long productNumber = productService.allProduct();
    return new ProductsDto(productDto, productNumber);
  }
}
