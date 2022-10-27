package kr.megaptera.makaogift.dtos;

import javax.validation.constraints.NotBlank;

public class OrderDto {
  private Long id;

  private String userId;

  @NotBlank(message = "성함을 입력해주세요")
  private String receiver;

  private int productNumber;

  private Long price;

  @NotBlank(message = "주소를 입력해주세요")
  private String address;

  private String message;

  private String manufacturer;

  private String option;

  private String productName;

  private String image;

  public OrderDto(Long id, String userId, String receiver, int productNumber,
                  Long price, String address, String message,
                  String manufacturer, String option, String productName, String image) {
    this.id = id;
    this.userId = userId;
    this.receiver = receiver;
    this.productNumber = productNumber;
    this.price = price;
    this.address = address;
    this.message = message;
    this.manufacturer = manufacturer;
    this.option = option;
    this.productName = productName;
    this.image = image;
  }

  public String getImage() {
    return image;
  }

  public Long getId() {
    return id;
  }

  public String getUserId() {
    return userId;
  }

  public String getReceiver() {
    return receiver;
  }

  public int getProductNumber() {
    return productNumber;
  }

  public Long getPrice() {
    return price;
  }

  public String getAddress() {
    return address;
  }

  public String getMessage() {
    return message;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public String getOption() {
    return option;
  }

  public String getProductName() {
    return productName;
  }
}
