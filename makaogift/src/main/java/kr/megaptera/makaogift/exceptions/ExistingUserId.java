package kr.megaptera.makaogift.exceptions;

public class ExistingUserId extends RuntimeException {
  public ExistingUserId() {
    super("해당 아이디는 사용할 수 없습니다");
  }
}
