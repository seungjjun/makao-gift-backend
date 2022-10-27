package kr.megaptera.makaogift.dtos;

public class TransactionFailedDto extends ErrorDto{
  public TransactionFailedDto() {
    super(1004, "거래내역을 불러올 수 없습니다.");
  }
}
