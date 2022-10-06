package kr.megaptera.makaogift.models;

import kr.megaptera.makaogift.dtos.TransactionDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

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

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public Transaction() {
  }

  public Transaction(String sender, String receiver, String manufacturer, String productName,
                     String option, int productNumber, Long price,
                     String address, String message) {
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

  public TransactionDto toDto() {
    return new TransactionDto(
        sender, receiver, productNumber,
        price, address, message, manufacturer,
        option, productName, createdAt
    );
  }
}
