package kr.megaptera.makaogift.models;

import kr.megaptera.makaogift.dtos.TransactionDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Transaction {
  @Id
  @GeneratedValue
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

  private String image;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public Transaction() {
  }

  public Transaction(
      String sender, String receiver, String manufacturer, String productName,
      String option, int productNumber, Long price, String address, String message,
      String image
  ) {
    this.sender = sender;
    this.receiver = receiver;
    this.manufacturer = manufacturer;
    this.productName = productName;
    this.option = option;
    this.productNumber = productNumber;
    this.price = price;
    this.address = address;
    this.message = message;
    this.image = image;
  }

  public Transaction(
      Long id, String sender, String receiver, int productNumber,
      Long price, String address, String message, String manufacturer,
      String option, String productName, LocalDateTime createdAt) {
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
  }

  public String getImage() {
    return image;
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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public TransactionDto toDto() {
    return new TransactionDto(
        id, sender, receiver, productNumber,
        price, address, message, manufacturer,
        option, productName, image,
        createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    );
  }
}
