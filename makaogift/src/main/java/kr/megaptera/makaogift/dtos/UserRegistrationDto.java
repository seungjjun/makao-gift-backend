package kr.megaptera.makaogift.dtos;

public class UserRegistrationDto {
  private String name;

  private String userId;

  private String password;

  private String confirmPassword;

  public UserRegistrationDto() {
  }

  public UserRegistrationDto(String name, String userId, String password, String confirmPassword) {
    this.name = name;
    this.userId = userId;
    this.password = password;
    this.confirmPassword = confirmPassword;
  }

  public String getName() {
    return name;
  }

  public String getUserId() {
    return userId;
  }

  public String getPassword() {
    return password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }
}
