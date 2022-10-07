package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.dtos.OrderDto;
import kr.megaptera.makaogift.dtos.OrderResultDto;
import kr.megaptera.makaogift.dtos.TransactionDto;
import kr.megaptera.makaogift.dtos.TransactionsDto;
import kr.megaptera.makaogift.exceptions.TransactionNotFound;
import kr.megaptera.makaogift.models.Transaction;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.services.OrderService;
import kr.megaptera.makaogift.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {
  private final OrderService orderService;

  private final TransactionService transactionService;

  public OrderController(OrderService orderService,
                         TransactionService transactionService) {
    this.orderService = orderService;
    this.transactionService = transactionService;
  }

  @GetMapping("/orders")
  public TransactionsDto list(
      @RequestParam(required = false, defaultValue = "1") Integer page
  ) {
    User user = new User("jel1y", "jun", 50_000L);

    List<Transaction> transactions = transactionService.list(user.userId(), page);

    List<TransactionDto> transactionDto = transactions.stream()
        .map(Transaction::toDto)
        .collect(Collectors.toList());

    Long transactionNumber = transactionService.transactions();

    return new TransactionsDto(transactionDto, transactionNumber);
  }

  @GetMapping("orders/{id}")
  public TransactionDto detail(
      @PathVariable Long id
  ) {
    Transaction transaction = transactionService.findTransaction(id);

    return transaction.toDto();
  }

  @PostMapping("/order")
  @ResponseStatus(HttpStatus.CREATED)
  public OrderResultDto order(
      @Valid @RequestBody OrderDto orderDto) {
    // TODO: 인증 후 처리 필요
    String currentUser = "jel1y";

    Long amount = orderService.order(currentUser, orderDto.getPrice(),
        orderDto.getReceiver(), orderDto.getManufacturer(),
        orderDto.getProductName(), orderDto.getOption(),
        orderDto.getProductNumber(), orderDto.getAddress(),
        orderDto.getMessage());

    return new OrderResultDto(amount);
  }

  @ExceptionHandler(TransactionNotFound.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String transactionNotFound() {
    return "거래내역을 불러올 수 없습니다.";
  }
}
