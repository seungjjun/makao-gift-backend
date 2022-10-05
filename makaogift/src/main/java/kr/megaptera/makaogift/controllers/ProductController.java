package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.dtos.ProductDto;
import kr.megaptera.makaogift.dtos.ProductsDto;
import kr.megaptera.makaogift.exceptions.ProductNotFound;
import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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

  @GetMapping("products/{id}")
  public ProductDto detail(
      @PathVariable Long id
  ){
    Product product = productService.findProduct(id);
    return new ProductDto(
        product.getId(),
        product.getManufacturer(),
        product.getName(),
        product.getOption(),
        product.getPrice());
  }

  @ExceptionHandler(ProductNotFound.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String productNotFound() {
    return "상품을 불러올 수 없습니다.";
  }
}
