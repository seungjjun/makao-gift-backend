package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.dtos.LoginRequestDto;
import kr.megaptera.makaogift.dtos.LoginResultDto;
import kr.megaptera.makaogift.exceptions.LoginFailed;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.services.LoginService;
import kr.megaptera.makaogift.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class SessionController {
  private final LoginService loginService;
  private final JwtUtil jwtUtil;

  public SessionController(LoginService loginService, JwtUtil jwtUtil) {
    this.loginService = loginService;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public LoginResultDto login(
      @RequestBody LoginRequestDto loginRequestDto
  ) {
    String userId = loginRequestDto.getUserId();
    String password = loginRequestDto.getPassword();

    User user = loginService.login(userId, password);

    String accessToken = jwtUtil.encode(userId);

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
