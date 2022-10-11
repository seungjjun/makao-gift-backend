package kr.megaptera.makaogift.dtos;

import javax.validation.constraints.NotBlank;

public class UserRegistrationDto {
  @NotBlank(message = "이름을 입력해주세요")
  private String name;

  @NotBlank(message = "아이디를 입력해주세요")
  private String userId;

  @NotBlank(message = "비밀번호를 입력해주세요")
  private String password;

  @NotBlank(message = "비밀번호를 입력해주세요")
  private String confirmPassword;

  public UserRegistrationDto() {
  }

  public UserRegistrationDto(
      String name, String userId, String password, String confirmPassword
  ) {
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
