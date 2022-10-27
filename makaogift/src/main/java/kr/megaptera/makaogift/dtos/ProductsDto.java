package kr.megaptera.makaogift.dtos;

import java.util.List;

public class ProductsDto {
  private final List<ProductDto> products;

  private final Long productNumber;

  public ProductsDto(List<ProductDto> products, Long productNumber) {
    this.products = products;
    this.productNumber = productNumber;
  }

  public List<ProductDto> getProducts() {
    return products;
  }

  public Long getProductNumber() {
    return productNumber;
  }
}
