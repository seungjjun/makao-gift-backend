package kr.megaptera.makaogift.models;

import kr.megaptera.makaogift.dtos.UserDto;
import kr.megaptera.makaogift.dtos.UserRegisteredDto;
import org.springframework.security.crypto.password.PasswordEncoder;

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

  private String password;

  private String encodedPassword;

  private String name;

  private Long amount;

  public User() {
  }

  public User(Long id, String userId, String password, String name, Long amount) {
    this.id = id;
    this.userId = userId;
    this.encodedPassword = password;
    this.name = name;
    this.amount = amount;
  }

  public User(String userId, String password, String name, Long amount) {
    this.userId = userId;
    this.encodedPassword = password;
    this.name = name;
    this.amount = amount;
  }

  public User(String name, String userId, String password) {
    this.name = name;
    this.userId = userId;
    this.password = password;
    this.amount = 50_000L;
  }

  public void pay(Long price) {
    if (price > amount) {
      return;
    }
    amount -= price;
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
    return new User(1L, "jel1y", "Qwe1234!", "tester", 50_000L);
  }

  public boolean authenticate(String password,
                              PasswordEncoder passwordEncoder) {
    return passwordEncoder.matches(password, encodedPassword);
  }

  public void changePassword(String password,
                             PasswordEncoder passwordEncoder) {
    encodedPassword = passwordEncoder.encode(password);
  }

  public UserRegisteredDto toRegisteredDto() {
    return new UserRegisteredDto(userId, name, amount);
  }
}
