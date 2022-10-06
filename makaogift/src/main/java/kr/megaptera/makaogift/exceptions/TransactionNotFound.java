package kr.megaptera.makaogift.exceptions;

public class TransactionNotFound extends RuntimeException{
  public TransactionNotFound() {
    super("거래내역을 불러올 수 없습니다.");
  }
}
