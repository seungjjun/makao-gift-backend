package kr.megaptera.makaogift.models;

import kr.megaptera.makaogift.dtos.ProductDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
  @Id
  @GeneratedValue
  private Long id;

  private String manufacturer;

  private String name;

  private String option;

  private Long price;

  private String image;

  public Product() {
  }

  public Product(Long id, String manufacturer, String name,
                 String option, Long price) {
    this.id = id;
    this.manufacturer = manufacturer;
    this.name = name;
    this.option = option;
    this.price = price;
  }

  public Product(Long id, String manufacturer, String name, String option, Long price, String image) {
    this.id = id;
    this.manufacturer = manufacturer;
    this.name = name;
    this.option = option;
    this.price = price;
    this.image = image;
  }

  public String getImage() {
    return image;
  }

  public Long getId() {
    return id;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public String getName() {
    return name;
  }

  public String getOption() {
    return option;
  }

  public Long getPrice() {
    return price;
  }

  public ProductDto toDto() {
    return new ProductDto(
        id, manufacturer, name, option, price, image
    );
  }
}
