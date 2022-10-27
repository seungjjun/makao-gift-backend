package kr.megaptera.makaogift.dtos;

import java.util.List;

public class TransactionsDto {
  private final List<TransactionDto> transactions;

  private final Long transactionNumber;

  public TransactionsDto(List<TransactionDto> transactions, Long transactionNumber) {
    this.transactions = transactions;
    this.transactionNumber = transactionNumber;
  }

  public List<TransactionDto> getTransactions() {
    return transactions;
  }

  public Long getTransactionNumber() {
    return transactionNumber;
  }
}
