package kr.megaptera.makaogift.interceptors;

import com.auth0.jwt.exceptions.JWTDecodeException;
import kr.megaptera.makaogift.exceptions.AuthenticationError;
import kr.megaptera.makaogift.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor implements HandlerInterceptor {
  private final JwtUtil jwtUtil;

  public AuthenticationInterceptor(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler) throws Exception {
    String authorization = request.getHeader("Authorization");

    if (authorization == null || !authorization.startsWith("Bearer ")) {
      return true;
    }

    String accessToken = authorization.substring("Bearer ".length());

    try {
      String userId = jwtUtil.decode(accessToken);

      request.setAttribute("userId", userId);
      return true;
    } catch (JWTDecodeException exception) {
      throw new AuthenticationError();
    }
  }
}
