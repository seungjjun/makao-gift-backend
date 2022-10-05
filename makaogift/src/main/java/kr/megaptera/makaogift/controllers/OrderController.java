package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.dtos.OrderDto;
import kr.megaptera.makaogift.dtos.OrderResultDto;
import kr.megaptera.makaogift.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping("/order")
  @ResponseStatus(HttpStatus.CREATED)
  public OrderResultDto order(
      @Valid @RequestBody OrderDto orderDto) {
    // TODO: 인증 후 처리 필요
    String currentUser = "jel1y";

    Long amount = orderService.order(currentUser, orderDto.getPrice());

    return new OrderResultDto(amount);
  }
}
