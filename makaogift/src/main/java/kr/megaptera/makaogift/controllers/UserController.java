package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.dtos.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @GetMapping("/user/me")
  public UserDto user() {
    return new UserDto("Tester", 50_000L);
  }
}
