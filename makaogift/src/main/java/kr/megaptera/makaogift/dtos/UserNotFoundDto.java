package kr.megaptera.makaogift.dtos;

public class UserNotFoundDto extends ErrorDto {
  public UserNotFoundDto() {
    super(1002, "아이디 혹은 비밀번호가 맞지 않습니다");
  }
}
