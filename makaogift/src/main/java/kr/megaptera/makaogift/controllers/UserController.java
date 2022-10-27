package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.dtos.ErrorDto;
import kr.megaptera.makaogift.dtos.ExistingUserIdDto;
import kr.megaptera.makaogift.dtos.UserDto;
import kr.megaptera.makaogift.dtos.UserNotFoundDto;
import kr.megaptera.makaogift.dtos.UserRegisteredDto;
import kr.megaptera.makaogift.dtos.UserRegistrationDto;
import kr.megaptera.makaogift.exceptions.ExistingUserId;
import kr.megaptera.makaogift.exceptions.UserNotFound;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    String name = userRegistrationDto.getName();
    String userId = userRegistrationDto.getUserId();
    String password = userRegistrationDto.getPassword();

    User user = userService.register(name, userId, password);

    return user.toRegisteredDto();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String registrationFailed(MethodArgumentNotValidException exception) {
    BindingResult errors = exception.getBindingResult();

    for (ObjectError error : errors.getAllErrors()) {

      return error.getDefaultMessage();
    }
    return "Registration Failed!";
  }

  @ExceptionHandler(UserNotFound.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto userNotFound() {
    return new UserNotFoundDto();
  }

  @ExceptionHandler(ExistingUserId.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto existingUserId() {
    return new ExistingUserIdDto();
  }
}
