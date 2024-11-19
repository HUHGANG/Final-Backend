package com.ssafy.ssafyhome.intercepter;

import com.ssafy.ssafyhome.domain.entity.Member;
import com.ssafy.ssafyhome.exception.UnauthorizedException;
import com.ssafy.ssafyhome.service.MemberService;
import com.ssafy.ssafyhome.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

  private final JWTUtil jwtUtil;
  private final MemberService memberService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    String bearerToken = request.getHeader("Authorization");

    if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
      return true;
    }

    String token = bearerToken.substring(7);
    Member authenticatedMember;

    if (jwtUtil.validateToken(token)) {
      authenticatedMember = memberService.selectById(jwtUtil.getId(token));
      if (authenticatedMember == null) {
        throw new UnauthorizedException("존재하지 않는 회원");
      }
      request.setAttribute("authenticatedMember", authenticatedMember);
    }

    return true;
  }
}