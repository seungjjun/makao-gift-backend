package kr.megaptera.makaogift.dtos;

public class UserRegisteredDto {
  private String userId;

  private String name;

  private Long amount;

  public UserRegisteredDto() {
  }

  public UserRegisteredDto(String userId, String name, Long amount) {
    this.userId = userId;
    this.name = name;
    this.amount = amount;
  }

  public String getUserId() {
    return userId;
  }

  public String getName() {
    return name;
  }

  public Long getAmount() {
    return amount;
  }
}
