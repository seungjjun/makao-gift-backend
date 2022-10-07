package kr.megaptera.makaogift.dtos;

import java.time.LocalDateTime;

public class TransactionDto {
  private Long id;

  private String sender;

  private String receiver;

  private int productNumber;

  private Long price;

  private String address;

  private String message;

  private String manufacturer;

  private String option;

  private String productName;

  private String createdAt;

  public TransactionDto() {
  }

  public TransactionDto(Long id, String sender, String receiver,
                        int productNumber, Long price, String address,
                        String message, String manufacturer, String option,
                        String productName, String createdAt) {
    this.id = id;
    this.sender = sender;
    this.receiver = receiver;
    this.productNumber = productNumber;
    this.price = price;
    this.address = address;
    this.message = message;
    this.manufacturer = manufacturer;
    this.option = option;
    this.productName = productName;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public String getSender() {
    return sender;
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

  public String getCreatedAt() {
    return createdAt;
  }
}
