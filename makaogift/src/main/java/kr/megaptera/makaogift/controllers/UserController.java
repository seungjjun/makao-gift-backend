package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.dtos.UserDto;
import kr.megaptera.makaogift.dtos.UserRegisteredDto;
import kr.megaptera.makaogift.dtos.UserRegistrationDto;
import kr.megaptera.makaogift.exceptions.ExistingUserId;
import kr.megaptera.makaogift.exceptions.UserNotFound;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/")
  public UserDto user(
      @RequestAttribute("userId") String userId
  ) {
    User user = userService.detail(userId);
    return user.toDto();
  }

  @PostMapping("/signup")
  @ResponseStatus(HttpStatus.CREATED)
  public UserRegisteredDto signup(
      @Valid @RequestBody UserRegistrationDto userRegistrationDto
  ) {
    User user = userService.register(userRegistrationDto);
    return user.toRegisteredDto();
  }

  @ExceptionHandler(UserNotFound.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String userNotFound() {
    return "아이디 혹은 비밀번호가 맞지 않습니다";
  }

  @ExceptionHandler(ExistingUserId.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String existingUserId() {
    return "해당 아이디는 사용할 수 없습니다";
  }
}
