package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.dtos.UserDto;
import kr.megaptera.makaogift.exceptions.UserNotFound;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user/me")
  public UserDto user() {
    User user = userService.detail("jel1y");
    return user.toDto();
  }

  @ExceptionHandler(UserNotFound.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String userNotFound() {
    return "아이디 혹은 비밀번호가 맞지 않습니다";
  }
}
