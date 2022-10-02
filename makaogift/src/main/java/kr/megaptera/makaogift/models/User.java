package kr.megaptera.makaogift.models;

public class User {
  private String id;

  private String name;

  private Long amount;

  public User(String id, String name, Long amount) {
    this.id = id;
    this.name = name;
    this.amount = amount;
  }

  public String id() {
    return id;
  }

  public String name() {
    return name;
  }

  public Long amount() {
    return amount;
  }
}
