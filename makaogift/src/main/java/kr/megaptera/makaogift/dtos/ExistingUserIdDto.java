package kr.megaptera.makaogift.dtos;

public class ExistingUserIdDto extends ErrorDto{
  public ExistingUserIdDto() {
    super(1003, "해당 아이디는 사용할 수 없습니다");
  }
}
