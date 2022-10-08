package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.dtos.LoginRequestDto;
import kr.megaptera.makaogift.dtos.LoginResultDto;
import kr.megaptera.makaogift.exceptions.LoginFailed;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {
  private final LoginService loginService;

  public SessionController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public LoginResultDto login(
      @RequestBody LoginRequestDto loginRequestDto
  ) {
    User user = loginService.login(
        loginRequestDto.getUserId(), loginRequestDto.getPassword());

    // TODO 토큰 처리 필요
    String accessToken = "accessToken";

    return new LoginResultDto(
       accessToken, user.name(), user.amount()
    );
  }

  @ExceptionHandler(LoginFailed.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String loginFailed() {
    return "아이디 혹은 비밀번호가 맞지 않습니다";
  }
}
