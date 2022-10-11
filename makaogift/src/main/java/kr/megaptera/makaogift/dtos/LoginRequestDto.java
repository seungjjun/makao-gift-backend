package kr.megaptera.makaogift.dtos;

import javax.validation.constraints.NotBlank;

public class LoginRequestDto {
  @NotBlank(message = "아이디를 입력해주세요")
  private String userId;

  @NotBlank(message = "비밀번호를 입력해주세요")
  private String password;

  public LoginRequestDto() {
  }

  public LoginRequestDto(String userId, String password) {
    this.userId = userId;
    this.password = password;
  }

  public String getUserId() {
    return userId;
  }

  public String getPassword() {
    return password;
  }
}
