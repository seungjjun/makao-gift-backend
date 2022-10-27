package kr.megaptera.makaogift.exceptions;

public class UserNotFound extends RuntimeException{
  public UserNotFound() {
    super("아이디 혹은 비밀번호가 맞지 않습니다");
  }
}
