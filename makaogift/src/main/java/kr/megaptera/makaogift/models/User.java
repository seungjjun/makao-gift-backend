package kr.megaptera.makaogift.models;

import kr.megaptera.makaogift.dtos.UserDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
public class User {
  @Id
  @GeneratedValue
  private Long id;

  private String userId;

  private String name;

  private Long amount;

  public User() {
  }

  public User(Long id, String userId, String name, Long amount) {
    this.id = id;
    this.userId = userId;
    this.name = name;
    this.amount = amount;
  }

  public Long id() {
    return id;
  }

  public String userId() {
    return userId;
  }

  public String name() {
    return name;
  }

  public Long amount() {
    return amount;
  }

  public UserDto toDto() {
    return new UserDto(name, amount);
  }

  public static User fake() {
    return new User(1L, "jel1y", "tester", 50_000L);
  }
}
