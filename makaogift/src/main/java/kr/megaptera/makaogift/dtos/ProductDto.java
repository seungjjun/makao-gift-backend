package kr.megaptera.makaogift.dtos;

public class ProductDto {
  private Long id;

  private String manufacturer;

  private String name;

  private String option;

  private Long price;

  private String image;

  public ProductDto() {
  }

  public ProductDto(Long id, String manufacturer, String name, String option, Long price) {
    this.id = id;
    this.manufacturer = manufacturer;
    this.name = name;
    this.option = option;
    this.price = price;
  }

  public ProductDto(Long id, String manufacturer, String name, String option, Long price, String image) {
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
}
