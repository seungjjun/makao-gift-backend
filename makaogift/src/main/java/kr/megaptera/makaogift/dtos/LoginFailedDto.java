package kr.megaptera.makaogift.dtos;

public class LoginFailedDto extends ErrorDto {
  public LoginFailedDto() {
    super(1001, "아이디 혹은 비밀번호가 맞지 않습니다");
  }
}
